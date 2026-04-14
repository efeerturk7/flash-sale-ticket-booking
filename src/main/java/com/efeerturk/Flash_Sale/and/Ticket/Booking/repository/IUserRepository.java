package com.efeerturk.Flash_Sale.and.Ticket.Booking.repository;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
