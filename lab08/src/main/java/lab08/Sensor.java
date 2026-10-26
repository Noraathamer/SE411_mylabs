package lab08;

import java.util.ArrayList;
import java.util.List;

public abstract class Sensor implements Subject, Cloneable {

    private String name;
    private double reading;
    private List<Observer> observers = new ArrayList<>();

    protected Sensor(String name) {
        this.name = name;
    }

    @Override
    public void register(Observer o) {
        if (o != null && !observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this);
        }
    }

    /** Sets a new reading; observers are notified only if the value changed. */
    public void setReading(double reading) {
        if (Double.compare(this.reading, reading) != 0) {
            this.reading = reading;
            notifyObservers();
        }
    }

    public double getReading() {
        return reading;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getObserverCount() {
        return observers.size();
    }

    /** Unit of measurement, e.g. "C" or "%" (polymorphic). */
    public abstract String getUnit();

    /** Type of sensor, e.g. "Temperature" (polymorphic). */
    public abstract String getType();

    /**
     * Clones the sensor. The clone keeps the name and reading but gets its own
     * EMPTY list of observers, so it is not observed by the original's observers.
     */
    @Override
    public Sensor clone() {
        try {
            Sensor copy = (Sensor) super.clone();
            copy.observers = new ArrayList<>();
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Sensor implements Cloneable", e);
        }
    }

    @Override
    public String toString() {
        return String.format("%s sensor '%s' = %.2f %s",
                getType(), name, reading, getUnit());
    }
}