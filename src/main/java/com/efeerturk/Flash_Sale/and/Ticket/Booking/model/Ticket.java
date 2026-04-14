package com.efeerturk.Flash_Sale.and.Ticket.Booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket extends BaseEntity {


    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;

    private String ticketCode;
    private BigDecimal priceAtPurchase;
    private LocalDateTime purchaseDate;

}
