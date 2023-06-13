package com.example.test.services;

import com.example.test.dao.interfaces.TourDao;
import com.example.test.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final TourDao tourDao;

    @Autowired
    public ReservationService(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    public List<Reservation> getReservationsByUserId(long userId) {
        return tourDao.getReservationsByUserId(userId);
    }

    public void reserveTour(long userId, long tourId, int amount) {
        tourDao.reserveTour(userId, tourId, amount);
    }

    public void cancelReservation(long userId, long tourId) {
        tourDao.cancelReservation(userId, tourId);
    }
}
