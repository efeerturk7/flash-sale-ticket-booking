package com.efeerturk.Flash_Sale.and.Ticket.Booking.controller;

public class RestBaseController {
    protected <T> RootEntity<T>ok(T payload){
        return RootEntity.ok(payload);
    }
    protected <T> RootEntity<T>error(String errorMessage){
        return RootEntity.error(errorMessage);
    }
}
