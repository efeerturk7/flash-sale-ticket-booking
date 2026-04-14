package com.efeerturk.Flash_Sale.and.Ticket.Booking.mapper;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoEvent;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.model.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEventMapper {
    DtoEvent toDto(Event event);

}
