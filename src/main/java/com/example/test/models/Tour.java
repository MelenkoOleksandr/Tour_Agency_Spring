package com.example.test.models;

import java.time.LocalDate;

public class Tour {
    private Long id;
    private Long travelAgentId;
    private TourType type;
    private String title;
    private String description;
    private String image;
    private int price;
    private int availablePlaces;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isHot;
    private int hotDiscount;
    private String departurePlace;
    private String country;

    public Tour(long travelAgentId, TourType type, String title, String description, String image, int price, int availablePlaces, LocalDate startDate, LocalDate endDate, boolean isHot, int hotDiscount, String departurePlace, String country) {
        this.travelAgentId = travelAgentId;
        this.type = type;
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.availablePlaces = availablePlaces;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isHot = isHot;
        this.hotDiscount = hotDiscount;
        this.departurePlace = departurePlace;
        this.country = country;
    }

    public Tour(TourType type, String title, String description, String image, int price, int availablePlaces, LocalDate startDate, LocalDate endDate, boolean isHot, int hotDiscount, String departurePlace, String country) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.availablePlaces = availablePlaces;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isHot = isHot;
        this.hotDiscount = hotDiscount;
        this.departurePlace = departurePlace;
        this.country = country;
    }

    public Tour() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTravelAgentId() {
        return travelAgentId;
    }

    public void setTravelAgentId(Long travelAgentId) {
        this.travelAgentId = travelAgentId;
    }

    public TourType getType() {
        return type;
    }

    public void setType(TourType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public int getHotDiscount() {
        return hotDiscount;
    }

    public void setHotDiscount(int hotDiscount) {
        this.hotDiscount = hotDiscount;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
