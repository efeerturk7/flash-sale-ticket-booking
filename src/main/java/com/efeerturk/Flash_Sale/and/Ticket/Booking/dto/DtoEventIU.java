package com.efeerturk.Flash_Sale.and.Ticket.Booking.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoEventIU {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    @PositiveOrZero
    private Integer totalTickets;
    @NotNull
    @PositiveOrZero
    private Integer availableTickets;
    @NotNull
    private BigDecimal price;
    @NotNull
    @Future
    private LocalDateTime saleStart;
    @NotNull
    @Future
    private LocalDateTime saleEnd;
}
