package com.efeerturk.Flash_Sale.and.Ticket.Booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoTicket {

    private String ticketCode;
    private BigDecimal priceAtPurchase;
    private LocalDateTime purchaseDate;
}
