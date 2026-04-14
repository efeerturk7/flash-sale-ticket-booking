package com.efeerturk.Flash_Sale.and.Ticket.Booking.controller;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoAuthResponse;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoLoginIU;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoRegisterIU;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.jwt.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest-api/user")
public class RestUserController extends RestBaseController {
    private final AuthService authService;
    @PostMapping("/register")
    public RootEntity<DtoAuthResponse>register(@Valid @RequestBody DtoRegisterIU request){
        return ok(authService.register(request));
    }
    @PostMapping("/authenticate")
    public RootEntity<DtoAuthResponse> authenticate(@Valid @RequestBody DtoLoginIU request){
        return ok(authService.authenticate(request));
    }
}
