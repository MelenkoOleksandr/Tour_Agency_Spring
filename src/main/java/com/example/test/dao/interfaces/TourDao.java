package com.example.test.dao.interfaces;

import com.example.test.models.Reservation;
import com.example.test.models.Tour;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourDao {
    List<Tour> getAllTours();
    Tour getTourById(long id);
    void addTour(long travelAgentId, Tour tour);
    void updateTour(Tour tour);
    void deleteTour(long id);
    void reserveTour(long userId, long tourId, int amount);
    public void cancelReservation(long userId, long tourId);
    public List<Reservation> getReservationsByUserId(long userId);
}
