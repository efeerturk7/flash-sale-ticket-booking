package com.efeerturk.Flash_Sale.and.Ticket.Booking.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exception<E> {
    private String path;

    private Date createTime;

    private String hostName;

    private E message;

}
