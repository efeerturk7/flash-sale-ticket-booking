package com.efeerturk.Flash_Sale.and.Ticket.Booking.service.impl;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoTicket;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.TicketPurchaseRequest;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.enums.MessageType;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.exception.BaseException;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.exception.ErrorMessage;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.mapper.ITicketMapper;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.model.Event;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.model.Ticket;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.model.User;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.repository.IEventRepository;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.repository.ITicketRepository;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.repository.IUserRepository;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.service.ITicketService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketService implements ITicketService {
    private final ITicketRepository ticketRepository;
    private final ITicketMapper ticketMapper;
    private final IEventRepository eventRepository;
    private final IUserRepository userRepository;
    private final RedisTemplate redisTemplate;
    private final RedissonClient redissonClient;
    private static final int MAX_TICKETS_PER_USER=2;
    @Transactional
    public DtoTicket purchaseTicket(Long userId, TicketPurchaseRequest request) {
        Long eventID=request.getEventId();
        checkRateLimit(userId);
        Event event=eventRepository.findById(eventID).orElseThrow(()-> new BaseException(new ErrorMessage(MessageType.EVENT_NOT_FOUND,eventID.toString())));
        if (event.getSaleStart().isAfter(LocalDateTime.now())){
            throw new RuntimeException("Ticket sales have not started yet for this event");
        }
        RLock lock=redissonClient.getLock("lock:user:"+userId+":event:"+eventID);
        try{
            if (lock.tryLock(5,10, TimeUnit.SECONDS)){
                int userTicketCount=ticketRepository.countByUserIdAndEventId(userId,eventID);
                if (userTicketCount>=MAX_TICKETS_PER_USER){
                    throw new BaseException(new ErrorMessage(MessageType.USER_TICKET_LIMIT_EXCEEDED,userId.toString()));
                }
                String stockKey="event:"+eventID+":stock";
                Long currentStock=redisTemplate.opsForValue().decrement(stockKey);
                if (currentStock!=null&&currentStock<0){
                    redisTemplate.opsForValue().increment(stockKey);
                    throw new BaseException(new ErrorMessage(MessageType.TICKET_SOLD_OUT,userId.toString()));
                }
                User user=userRepository.getReferenceById(userId);
                Ticket ticket=ticketMapper.toTicketEntity(user,event);
                ticketRepository.save(ticket);
                event.setAvailableTickets(currentStock.intValue());
                eventRepository.save(event);
                String leaderBoardKey="leaderboard:top_buyers";
                redisTemplate.opsForZSet().incrementScore(leaderBoardKey,user.getEmail(),1);
                log.info("The ticket was purchased successfully.  UserID: {}, EventID: {}, CurrentStock: {}", userId, eventID,currentStock);
                return ticketMapper.toDto(ticket);
            }else {
                throw new BaseException(new ErrorMessage(MessageType.SYSTEM_IS_TOO_BUSY,userId.toString()));
            }

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new BaseException(new ErrorMessage(MessageType.TRANSACTION_WAS_INTERRUPTED,userId.toString()));
        }finally {
            if (lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }



    }
    private void checkRateLimit(Long userId){
        RRateLimiter rateLimiter=redissonClient.getRateLimiter("rateLimiter:user:"+userId);
        rateLimiter.trySetRate(RateType.OVERALL,3,1, RateIntervalUnit.SECONDS);
        if (!rateLimiter.tryAcquire()){
            log.warn("Rate limit exceeded! UserID: {}",userId);
            throw new BaseException(new ErrorMessage(MessageType.RATE_LIMIT_EXCEEDED,userId.toString()));
        }
    }

}
