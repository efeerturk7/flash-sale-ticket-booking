package com.efeerturk.Flash_Sale.and.Ticket.Booking.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;



@Data
public class DtoRegisterIU {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
