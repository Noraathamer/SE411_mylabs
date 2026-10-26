package lab08;

public class HumiditySensor extends Sensor {

    public HumiditySensor(String name) {
        super(name);
    }

    @Override
    public String getUnit() {
        return "%";
    }

    @Override
    public String getType() {
        return "Humidity";
    }

    @Override
    public HumiditySensor clone() {
        return (HumiditySensor) super.clone();
    }
}