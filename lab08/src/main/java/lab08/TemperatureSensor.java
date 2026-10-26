package lab08;

public class TemperatureSensor extends Sensor {

    public TemperatureSensor(String name) {
        super(name);
    }

    @Override
    public String getUnit() {
        return "C";
    }

    @Override
    public String getType() {
        return "Temperature";
    }

    @Override
    public TemperatureSensor clone() {
        return (TemperatureSensor) super.clone();
    }
}