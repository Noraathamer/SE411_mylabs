package edu.psu.se411.lab07.model;

import edu.psu.se411.lab07.util.Config;

public class FlightBooking extends Booking {
	
	private double basePrice;
    private double luggageWeight;
    private double includedLuggageWeight;

    public FlightBooking(String bookingId, String customerName,
            java.util.Date bookingDate, String destination,
            double basePrice, double luggageWeight,
            double includedLuggageWeight) {

        super(bookingId, customerName, bookingDate, destination);
        setBasePrice(basePrice);
        setLuggageWeight(luggageWeight);
        setIncludedLuggageWeight(includedLuggageWeight);
    }

    @Override
    public double calculateTotalPrice() {
        return (basePrice
                + Math.max(0, luggageWeight - includedLuggageWeight)
                * Config.EXTRA_LUGGAGE_RATE)
                * (1 + Config.TAX_RATE);
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        if (basePrice < 0) {
            throw new IllegalArgumentException(
                    "Base price cannot be negative");
        }

        this.basePrice = basePrice;
    }

    public double getLuggageWeight() {
        return luggageWeight;
    }

    public void setLuggageWeight(double luggageWeight) {
        if (luggageWeight < 0
                || luggageWeight > Config.MAX_LUGGAGE_WEIGHT) {
            throw new IllegalArgumentException(
                    "Luggage weight cannot be negative");
        }

        this.luggageWeight = luggageWeight;
    }

    public double getIncludedLuggageWeight() {
        return includedLuggageWeight;
    }

    public void setIncludedLuggageWeight(double includedLuggageWeight) {
        if (includedLuggageWeight < 0
                || includedLuggageWeight > Config.MAX_LUGGAGE_WEIGHT) {
            throw new IllegalArgumentException(
                    "Included luggage weight cannot be negative");
        }

        this.includedLuggageWeight = includedLuggageWeight;
    }
}
