package lab08;
// فكرته: يستقبل التحديث في update، ويتأكد أن مصدره Sensor، ثم يستدعي display. كل مراقب بنخليه يكتب طريقته الخاصة في عرض البيانات.
public abstract class SensorObserver implements Observer {

    private final String name;

    protected SensorObserver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Sensor) {
            Sensor sensor = (Sensor) subject;
            display(sensor);
        }
    }

    /** What the concrete observer does with the new sensor data. */
    protected abstract void display(Sensor sensor);
}