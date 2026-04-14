package com.efeerturk.Flash_Sale.and.Ticket.Booking.config;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.enums.MessageType;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.exception.BaseException;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.exception.ErrorMessage;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.jwt.CustomUserDetails;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.model.User;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final IUserRepository userRepository;
    @Bean
    public  UserDetailsService userDetailsSerService(){
        return new UserDetailsService(){
        @Override
        public UserDetails loadUserByUsername(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.EMAIL_NOT_FOUND,email));
        }
        return new CustomUserDetails(user.get());
        }
        };

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
