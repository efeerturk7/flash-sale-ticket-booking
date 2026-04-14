package com.efeerturk.Flash_Sale.and.Ticket.Booking.mapper;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoTicket;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.model.Event;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.model.Ticket;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, LocalDateTime.class})
public interface ITicketMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "event", source = "event")
    @Mapping(target = "priceAtPurchase", source = "event.price")
    @Mapping(target = "ticketCode", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "purchaseDate", expression = "java(LocalDateTime.now())")
    Ticket toTicketEntity(User user, Event event);
    DtoTicket toDto(Ticket ticket);

}
