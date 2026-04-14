package com.efeerturk.Flash_Sale.and.Ticket.Booking.model;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends BaseEntity {


        private String firstName;

        private String lastName;


        private String email;


        private String password;

        @Enumerated(EnumType.STRING)
        private Role role;

        private LocalDateTime createdAt;
}
