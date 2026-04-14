package com.efeerturk.Flash_Sale.and.Ticket.Booking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
    NO_RECORD_EXIST("1004" , "no record found"),
    TOKEN_IS_EXPIRED("1005" , "token expired"),
    USERNAME_NOT_FOUND("1006" , "username not found"),
    USERNAME_OR_PASSWORD_INVALID("1007" , "username or password is incorrect"),
    REFRESH_TOKEN_NOT_FOUND("1008" , "refresh token not found"),
    REFRESH_TOKEN_IS_EXPIRED("1009" , "refresh token expired"),
    GENERAL_EXCEPTION("9999" , "A general error occurred"),
    EVENT_NOT_FOUND("1010" , "event not found"),
    RATE_LIMIT_EXCEEDED("1011" , "rate limit exceeded"),
    USER_TICKET_LIMIT_EXCEEDED("1012" , "user ticket limit exceeded"),
    TICKET_SOLD_OUT("1013" , "unfortunately,the tickets sold out"),
    SYSTEM_IS_TOO_BUSY("1014" , "system is too busy"),
    TRANSACTION_WAS_INTERRUPTED("1015" , "transaction was interrupted"),
    EMAIL_NOT_FOUND("1016" , "email not found"),
    USER_NOT_FOUND("1017" , "user not found");
    private String code;
    private String message;
}
