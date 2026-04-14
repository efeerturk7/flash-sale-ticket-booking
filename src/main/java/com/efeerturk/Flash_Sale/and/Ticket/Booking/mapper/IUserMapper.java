package com.efeerturk.Flash_Sale.and.Ticket.Booking.mapper;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoLoginIU;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoRegisterIU;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoUser;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    DtoUser toDtoUser(User user);
    User toEntity(DtoRegisterIU dtoRegisterIU);
}
