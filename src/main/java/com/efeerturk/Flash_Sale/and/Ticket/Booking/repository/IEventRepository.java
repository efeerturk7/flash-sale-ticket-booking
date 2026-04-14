package com.efeerturk.Flash_Sale.and.Ticket.Booking.repository;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface IEventRepository extends JpaRepository<Event,Long> {
    @Query("SELECT e FROM Event e WHERE e.saleStart > :now")
    Page<Event> findActiveEventsWithPagination(@Param("now") LocalDateTime now, Pageable pageable);

}


