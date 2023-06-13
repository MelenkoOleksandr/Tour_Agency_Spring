package com.example.test.controllers;

import com.example.test.models.Reservation;
import com.example.test.models.User;
import com.example.test.services.ReservationService;
import com.example.test.services.TourService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(HttpServletRequest request) {
        long authUserId = (int) request.getAttribute("userId");
        return ResponseEntity.ok().body(reservationService.getReservationsByUserId(authUserId));
    }


    @PostMapping("/{tourId}")
    public ResponseEntity<String> createReservation(HttpServletRequest request, @PathVariable("tourId") long tourId, @RequestBody int amount) {
        long authUserId = (int) request.getAttribute("userId");
        reservationService.reserveTour(authUserId, tourId, amount);
        return ResponseEntity.ok().body("Successfully reserved tour with id " + tourId);
    }

    @DeleteMapping("/{tourId}")
    public ResponseEntity<String> deleteReservation(HttpServletRequest request, @PathVariable("tourId") long tourId) {
        long authUserId = (int) request.getAttribute("userId");
        reservationService.cancelReservation(authUserId, tourId);
        return ResponseEntity.ok().body("Successfully cancelled reservation with tourId " + tourId);
    }
}
