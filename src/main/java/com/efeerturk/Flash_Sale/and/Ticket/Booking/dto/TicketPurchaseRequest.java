package com.efeerturk.Flash_Sale.and.Ticket.Booking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketPurchaseRequest {
    @NotNull
    private Long eventId;
}
