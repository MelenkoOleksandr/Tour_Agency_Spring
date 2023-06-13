package com.example.test.services;

import com.example.test.dao.interfaces.TourDao;
import com.example.test.models.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {
    private final TourDao tourDao;

    @Autowired
    public TourService(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    public List<Tour> getAllTours() {
        return tourDao.getAllTours();
    }

    public Tour getTourById(long id) {
        return tourDao.getTourById(id);
    }

    public void addTour(long travelAgentId, Tour tour) {
        tourDao.addTour(travelAgentId, tour);
    }

    public void updateTour(Tour tour) {
        tourDao.updateTour(tour);
    }

    public void deleteTour(long id)  {
        tourDao.deleteTour(id);
    }
}
