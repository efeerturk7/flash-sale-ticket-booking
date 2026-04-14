package com.efeerturk.Flash_Sale.and.Ticket.Booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoEvent {
    private String name;
    private String description;
    private Integer totalTickets;
    private Integer availableTickets;
    private BigDecimal price;
    private LocalDateTime saleStart;
    private LocalDateTime saleEnd;
    private LocalDateTime createdAt;
}
