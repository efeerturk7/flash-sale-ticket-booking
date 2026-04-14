package com.efeerturk.Flash_Sale.and.Ticket.Booking.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError <E> {
    private Integer status;

    private Exception<E> exception;
}
