package com.efeerturk.Flash_Sale.and.Ticket.Booking.jwt;

import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoAuthResponse;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoLoginIU;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.dto.DtoRegisterIU;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.enums.MessageType;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.enums.Role;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.exception.BaseException;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.exception.ErrorMessage;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.model.User;
import com.efeerturk.Flash_Sale.and.Ticket.Booking.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    public DtoAuthResponse register(DtoRegisterIU request){
        User user=new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(new CustomUserDetails(user));
        return new DtoAuthResponse(jwtToken);
    }
    public DtoAuthResponse authenticate(DtoLoginIU request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user=userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, request.getEmail())));
        String jwtToken = jwtService.generateToken(new CustomUserDetails(user));
        return new DtoAuthResponse(jwtToken);
    }
}
