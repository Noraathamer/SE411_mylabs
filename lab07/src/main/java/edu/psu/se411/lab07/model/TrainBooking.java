package edu.psu.se411.lab07.model;

import edu.psu.se411.lab07.util.Config;
import edu.psu.se411.lab07.util.SeatClass;

public class TrainBooking extends Booking {

    private SeatClass seatClass;
    private double distance;

    public TrainBooking(String bookingId, String customerName,
            java.util.Date bookingDate, String destination,
            SeatClass seatClass, double distance) {

        super(bookingId, customerName, bookingDate, destination);
        setSeatClass(seatClass);
        setDistance(distance);
    }

    @Override
    public double calculateTotalPrice() {
        return (distance
                * (seatClass == SeatClass.FIRST_CLASS
                ? Config.TRAIN_FIRST_CLASS_RATE
                : Config.TRAIN_STANDARD_RATE))
                * (1 + Config.TAX_RATE);
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(SeatClass seatClass) {
        if (seatClass == null) {
            throw new IllegalArgumentException(
                    "Seat class cannot be null");
        }

        this.seatClass = seatClass;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        if (distance < 0 || distance > Config.MAX_TRAIN_DISTANCE) {
            throw new IllegalArgumentException(
                    "Distance cannot be negative or greater than 2000");
        }

        this.distance = distance;
    }
}