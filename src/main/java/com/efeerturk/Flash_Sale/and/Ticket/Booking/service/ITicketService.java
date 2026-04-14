package com.efeerturk.Flash_Sale.and.Ticket.Booking.service;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoTicket;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.TicketPurchaseRequest;

public interface ITicketService {
     DtoTicket purchaseTicket(Long userId, TicketPurchaseRequest request);
}
