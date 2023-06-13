package com.example.test.dao.impl;

import com.example.test.dao.interfaces.TourDao;
import com.example.test.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TourDaoImpl implements TourDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TourDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tour> getAllTours() {
        String sql = "SELECT * FROM tours";
        return jdbcTemplate.query(sql, this::mapTourRow);
    }

    @Override
    public Tour getTourById(long id) {
        String sql = "SELECT * FROM tours WHERE id = ?";
        return jdbcTemplate.query(sql, this::mapTourRow, id).get(0);
    }

    @Override
    public void addTour(long travelAgentId, Tour tour) {
        String sql = "INSERT INTO tours (travel_agent_id, tour_type, title, description, image, price, available_places, start_date, end_date, is_hot, hot_discount, departure_place, country) VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
        jdbcTemplate.update(sql, travelAgentId, tour.getType().toString(), tour.getTitle(), tour.getDescription(), tour.getImage(), tour.getPrice(), tour.getAvailablePlaces(), tour.getStartDate(), tour.getEndDate(), tour.isHot(), tour.getHotDiscount(), tour.getDeparturePlace(), tour.getCountry());
    }

    @Override
    public void updateTour(Tour tour) {
        String sql = "UPDATE tours SET travel_agent_id = ?, tour_type = ?, title = ?, description = ?, image = ?, price = ?, available_places = ?, start_date = ?, end_date = ?, is_hot = ?, hot_discount = ?, departure_place = ?, country = ? WHERE id = ?";
        jdbcTemplate.update(sql, tour.getTravelAgentId(), tour.getType().toString(), tour.getTitle(), tour.getDescription(), tour.getImage(), tour.getPrice(), tour.getAvailablePlaces(), tour.getStartDate(), tour.getEndDate(), tour.isHot(), tour.getHotDiscount(), tour.getDeparturePlace(), tour.getCountry(), tour.getId());
    }

    @Override
    public void deleteTour(long id) {
        String sql = "DELETE FROM tours WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void reserveTour(long userId, long tourId, int amount) {
        String reserveTourSql = "INSERT INTO users_tours (user_id, tour_id, amount) VALUES (? , ? , ?)";
        jdbcTemplate.update(reserveTourSql, userId, tourId, amount);
        int availablePlaces = getTourById(tourId).getAvailablePlaces();
        String updateTourAvailablePlacesSql = "UPDATE tours SET available_places = ? WHERE id = ?";
        jdbcTemplate.update(updateTourAvailablePlacesSql, availablePlaces - amount, tourId);
    }

    @Override
    public void cancelReservation(long userId, long tourId) {
        String cancelReservationSql = "DELETE FROM users_tours WHERE user_id = ? AND tour_id = ?";
        jdbcTemplate.update(cancelReservationSql, userId, tourId);
        int currentAvailablePlaces = getTourById(tourId).getAvailablePlaces();
        String updateTourAvailablePlacesSql = "UPDATE tours SET available_places = ? WHERE id = ?";
        int availablePlaces = currentAvailablePlaces + getReservationAmount(userId, tourId);
        jdbcTemplate.update(updateTourAvailablePlacesSql, availablePlaces, tourId);

    }

    @Override
    public List<Reservation> getReservationsByUserId(long userId) {
        String sql = "SELECT * FROM tours INNER JOIN users_tours ON tours.id = users_tours.tour_id WHERE users_tours.user_id = ?";

        return jdbcTemplate.query(sql, this::mapReservationRow, userId);
    }

    private int getReservationAmount(long userId, long tourId) {
        String sql = "SELECT amount FROM users_tours WHERE user_id = ? AND tour_id = ?";
        return (int) jdbcTemplate.query(sql, this::mapReservationAmountRow, userId, tourId).get(0);
    }

    private Reservation mapReservationRow(ResultSet resultSet, int i) throws SQLException {
        return new Reservation(handleReadTour(resultSet), resultSet.getInt("amount"));
    }

    private Tour mapTourRow(ResultSet rs, int rowNum) throws SQLException {
        return handleReadTour(rs);
    }

    private Object mapReservationAmountRow(ResultSet resultSet, int i) {
        try {
            return resultSet.getInt("amount");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Tour handleReadTour(ResultSet rs) throws SQLException {
        Tour tour = new Tour();
        tour.setId(rs.getLong("id"));
        tour.setTravelAgentId(rs.getLong("travel_agent_id"));
        tour.setType(TourType.valueOf(rs.getString("tour_type")));
        tour.setTitle(rs.getString("title"));
        tour.setDescription(rs.getString("description"));
        tour.setImage(rs.getString("image"));
        tour.setPrice(rs.getInt("price"));
        tour.setAvailablePlaces(rs.getInt("available_places"));
        tour.setStartDate(rs.getDate("start_date").toLocalDate());
        tour.setEndDate(rs.getDate("end_date").toLocalDate());
        tour.setHot(rs.getBoolean("is_hot"));
        tour.setHotDiscount(rs.getInt("hot_discount"));
        tour.setDeparturePlace(rs.getString("departure_place"));
        tour.setCountry(rs.getString("country"));
        return tour;
    }
}
