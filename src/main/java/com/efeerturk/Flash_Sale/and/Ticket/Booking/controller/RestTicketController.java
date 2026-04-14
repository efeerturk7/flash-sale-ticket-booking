package com.efeerturk.Flash_Sale.and.Ticket.Booking.controller;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoTicket;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.TicketPurchaseRequest;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.jwt.CustomUserDetails;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.service.impl.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest-api/ticket")
public class RestTicketController extends RestBaseController{
    private final TicketService ticketService;
    @PostMapping("/purchase")
    public RootEntity<DtoTicket>purchaseTicket(@AuthenticationPrincipal CustomUserDetails currentUser, @Valid @RequestBody TicketPurchaseRequest request){
        Long userID=currentUser.getUser().getId();
        return ok(ticketService.purchaseTicket(userID,request));
    }
}
