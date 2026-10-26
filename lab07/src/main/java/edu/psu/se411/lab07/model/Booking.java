package edu.psu.se411.lab07.model;

import java.util.Date;

public abstract class Booking {

    private String bookingId;
    private String customerName;
    private Date bookingDate;
    private String destination;

    public Booking(String bookingId, String customerName,
                   Date bookingDate, String destination) {
        setBookingId(bookingId);
        setCustomerName(customerName);
        setBookingDate(bookingDate);
        setDestination(destination);
    }

    public abstract double calculateTotalPrice();

    public String getBookingId() {
        return bookingId;
    }

    private void setBookingId(String bookingId) {
        if (bookingId == null || bookingId.isEmpty()) {
            throw new IllegalArgumentException(
                "Booking ID cannot be null or empty");
        }

        this.bookingId = bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalArgumentException(
                "Customer name cannot be null or empty");
        }

        this.customerName = customerName;
    }

    public Date getBookingDate() {
        if (bookingDate == null && bookingDate.after(new Date())) {
            throw new IllegalArgumentException(
                "Booking date cannot be null");
        }

        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}