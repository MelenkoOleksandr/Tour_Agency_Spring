package com.example.test.controllers;

import com.example.test.models.Tour;
import com.example.test.services.TourService;
import com.example.test.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
public class TourController {
    private final TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tour> getTourById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(tourService.getTourById(id));
    }

    @GetMapping
    public ResponseEntity<List<Tour>> getAllTours() {
        return ResponseEntity.ok().body(tourService.getAllTours());
    }

    @PostMapping
    public ResponseEntity<String> createTour(HttpServletRequest request, @RequestBody Tour tour) {
        long authUserId = (int) request.getAttribute("userId");
        String userType = request.getAttribute("userType").toString();
        if (!userType.equals("ADMIN") && !userType.equals("TRAVEL_AGENT")) {
            return ResponseEntity.badRequest().body("You have to be an admin or travel agent to create a tour");
        }

        tourService.addTour(authUserId, tour);
        return ResponseEntity.ok().body("Create a tour");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTour(HttpServletRequest request, @PathVariable("id") long id, @RequestBody Tour tour) {
        long authUserId = (int) request.getAttribute("userId");
        String userType = request.getAttribute("userType").toString();
        if (!userType.equals("ADMIN") && !userType.equals("TRAVEL_AGENT") && authUserId != tour.getTravelAgentId()) {
            return ResponseEntity.badRequest().body("You have to be an admin or travel agent to update a tour");
        }

        tourService.updateTour(tour);
        return ResponseEntity.ok().body("Update tour with id " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTour(HttpServletRequest request, @PathVariable("id") long id) {
        long authUserId = (int) request.getAttribute("userId");
        String userType = request.getAttribute("userType").toString();
        if (!userType.equals("ADMIN") && !userType.equals("TRAVEL_AGENT") && authUserId != tourService.getTourById(id).getTravelAgentId()) {
            return ResponseEntity.badRequest().body("You have to be an admin or travel agent to delete a tour");
        }

        tourService.deleteTour(id);
        return ResponseEntity.ok().body("Delete tour with id " + id);
    }
}
