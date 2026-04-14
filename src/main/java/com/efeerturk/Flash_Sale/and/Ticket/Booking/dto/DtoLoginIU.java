package com.efeerturk.Flash_Sale.and.Ticket.Booking.dto;


import jakarta.validation.constraints.NotBlank;

import lombok.Data;



@Data
public class DtoLoginIU {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
