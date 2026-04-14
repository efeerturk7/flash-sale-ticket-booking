package com.efeerturk.Flash_Sale.and.Ticket.Booking.service.impl;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoUser;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoRegisterIU;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.mapper.IUserMapper;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.model.User;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.repository.IUserRepository;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    public DtoUser register(DtoRegisterIU newUser){
        User newUserEntity = userMapper.toEntity(newUser);
        User createdUserEntity = userRepository.save(newUserEntity);
        DtoUser dtoUser = userMapper.toDtoUser(createdUserEntity);
        return dtoUser;
    }
    public DtoUser getUser(Long userId){
        User getUser=userRepository.findById(userId).get();
        DtoUser dtoUser = userMapper.toDtoUser(getUser);
        return dtoUser;
    }


}
