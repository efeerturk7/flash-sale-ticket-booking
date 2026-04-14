package com.efeerturk.Flash_Sale.and.Ticket.Booking.service;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoRegisterIU;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoUser;

public interface IUserService {
    DtoUser register(DtoRegisterIU newUser);
    DtoUser getUser(Long userId);
}
