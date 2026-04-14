package com.efeerturk.Flash_Sale.and.Ticket.Booking.controller;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoEvent;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoEventIU;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.service.impl.EventService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest-api/event")
public class RestEventController extends RestBaseController {
    private final EventService eventService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public RootEntity<DtoEvent>createEvent(@Valid @RequestBody DtoEventIU request){
        return ok(eventService.createEvent(request));
    }
    @GetMapping("/active")
    public RootEntity<Page<DtoEvent>>getActiveEvents(@Valid @RequestParam int page, @RequestParam int pageSize){
        return ok(eventService.getActiveEvents(page,pageSize));
    }
}
