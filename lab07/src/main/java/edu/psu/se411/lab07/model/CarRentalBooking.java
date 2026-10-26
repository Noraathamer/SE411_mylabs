package edu.psu.se411.lab07.model;

import java.util.Date;

import edu.psu.se411.lab07.util.Config;

public class CarRentalBooking extends Booking {

    private double dailyRentalRate;
    private Integer rentalDays;

    public CarRentalBooking(String bookingId, String customerName,
            Date travelDate, String destinationCity,
            double dailyRentalRate, int rentalDays) {

        super(bookingId, customerName, travelDate, destinationCity);
        this.dailyRentalRate = dailyRentalRate;
        this.rentalDays = rentalDays;
    }

    public double getDailyRentalRate() {
        return dailyRentalRate;
    }

    public Integer getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    @Override
    public double calculateTotalPrice()
            throws IllegalArgumentException {

        // 1. Missing?
        if (rentalDays == null) {
            throw new IllegalArgumentException(
                    "Number of rental days not provided for booking "
                    + getBookingId());
        }

        // 2. Out of range?
        if (rentalDays < Config.MIN_RENTAL_DAYS
                || rentalDays > Config.MAX_RENTAL_DAYS) {
            throw new IllegalArgumentException(
                    "Rental days must be between "
                    + Config.MIN_RENTAL_DAYS + " and "
                    + Config.MAX_RENTAL_DAYS
                    + ", but was " + rentalDays);
        }

        // 3. Calculate
        return dailyRentalRate * rentalDays;
    }

    @Override
    public String toString() {
        return "CarRentalBooking [" + super.toString()
                + " | dailyRate=" + dailyRentalRate
                + " | days=" + rentalDays + "]";
    }
}