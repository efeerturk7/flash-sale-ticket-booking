package com.efeerturk.Flash_Sale.and.Ticket.Booking.dto;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private LocalDateTime createdAt;
}
