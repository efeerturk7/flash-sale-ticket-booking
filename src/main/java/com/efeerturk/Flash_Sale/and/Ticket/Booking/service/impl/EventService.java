package com.efeerturk.Flash_Sale.and.Ticket.Booking.service.impl;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoEvent;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoEventIU;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.enums.MessageType;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.exception.BaseException;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.exception.ErrorMessage;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.mapper.IEventMapper;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.model.Event;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.repository.IEventRepository;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.service.IEventService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService implements IEventService {
    private final IEventRepository eventRepository;
    private final RedisTemplate redisTemplate;
    private final IEventMapper  eventMapper;
    @Transactional
    public DtoEvent createEvent(DtoEventIU request){
        log.info("New event is creating... {}",request);
        Event event=new Event();
        BeanUtils.copyProperties(request,event);
        event.setCreatedAt(LocalDateTime.now());
        Event savedEvent = eventRepository.save(event);
        String redisStockKey="event:"+savedEvent.getId()+":stock";
        redisTemplate.opsForValue().set(redisStockKey,String.valueOf(savedEvent.getTotalTickets()));
        log.info("New event was created successfully. Redis stock : {}   EventID: {}",savedEvent.getTotalTickets(),savedEvent.getId());
        return eventMapper.toDto(savedEvent);
    }
    @Transactional
    public Page<DtoEvent> getActiveEvents(int page,int size){
        Pageable pageable= PageRequest.of(page,size, Sort.by("saleStart").ascending());
        return eventRepository.findActiveEventsWithPagination(LocalDateTime.now(),pageable).map(eventMapper::toDto);

    }
    @Transactional
    public DtoEvent getEventDetails(Long id){
        Event event=eventRepository.findById(id).orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.EVENT_NOT_FOUND,id.toString())));
        return eventMapper.toDto(event);
    }


}
