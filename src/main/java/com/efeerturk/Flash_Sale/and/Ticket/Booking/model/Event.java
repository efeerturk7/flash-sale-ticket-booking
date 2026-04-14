package com.efeerturk.Flash_Sale.and.Ticket.Booking.model;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event extends BaseEntity {


    private String name;
    private String description;
    private Integer totalTickets;
    private Integer availableTickets;
    private BigDecimal price;
    private LocalDateTime saleStart;
    private LocalDateTime saleEnd;
    private LocalDateTime createdAt;


}
