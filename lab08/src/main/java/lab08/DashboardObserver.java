package lab08;
//وظيفته: كلما وصله تحديث، يطبع اسم المراقب ونوع الحساس واسمه وقراءته ووحدته في الـConsole. و%.2f تعرض القراءة بمنزلتين عشريتين.
public class DashboardObserver extends SensorObserver {

    public DashboardObserver(String name) {
        super(name);
    }

    @Override
    protected void display(Sensor sensor) {
        System.out.printf("[DASHBOARD %s] %-11s | %-12s | %6.2f %s%n",
                getName(), sensor.getType(), sensor.getName(),
                sensor.getReading(), sensor.getUnit());
    }
}