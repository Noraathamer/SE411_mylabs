package edu.psu.se411.lab07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab07.model.Booking;
import edu.psu.se411.lab07.model.CarRentalBooking;
import edu.psu.se411.lab07.model.FlightBooking;
import edu.psu.se411.lab07.model.TrainBooking;
import edu.psu.se411.lab07.util.SeatClass;

public class MainApp {

    static Logger logger = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {

        logger.info("Application is starting...");

        Booking fbooking = new FlightBooking(
                "B001", "John Doe", new java.util.Date(),
                "New York", 200.0, 30.0, 20.0);

        fbooking.calculateTotalPrice();
        System.out.println("Total Price: "
                + fbooking.calculateTotalPrice());

        Booking cbooking = new CarRentalBooking(
                "B002", "Jane Smith", new java.util.Date(),
                "Los Angeles", 50.0, 10);

        cbooking.calculateTotalPrice();
        System.out.println("Total Price: "
                + cbooking.calculateTotalPrice());

        SeatClass seatClass = SeatClass.STANDARD_CLASS;

        Booking tbooking = new TrainBooking(
                "B003", "Alice Johnson", new java.util.Date(),
                "Chicago", seatClass, 100.0);

        System.out.println("Total Price: "
                + tbooking.calculateTotalPrice());
    }
}