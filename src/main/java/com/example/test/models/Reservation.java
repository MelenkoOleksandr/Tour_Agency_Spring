package com.example.test.models;

public class Reservation {
    private Tour tour;
    private int amount;

    public Reservation(Tour tour, int amount) {
        this.tour = tour;
        this.amount = amount;
    }

    public Reservation() {

    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
