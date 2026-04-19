package com.efeerturk.Flash_Sale.and.Ticket.Booking.repository;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket,Long> {
    int countByUserIdAndEventId(Long userId, Long eventId);

}
