package com.efeerturk.Flash_Sale.and.Ticket.Booking.service;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoEvent;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoEventIU;
import org.springframework.data.domain.Page;

public interface IEventService {
    DtoEvent createEvent(DtoEventIU request);
    Page<DtoEvent> getActiveEvents(int page, int size);
    DtoEvent getEventDetails(Long id);
}
