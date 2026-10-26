package lab08;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Application is starting...");
        Random random = new Random();

        // 1. Create the subjects (sensors)
        TemperatureSensor temp = new TemperatureSensor("Lab-Temp");
        HumiditySensor humidity = new HumiditySensor("Lab-Humidity");

        // 2. Create the observers
        Observer dashboard = new DashboardObserver("Main");
        Observer log = new LoggerObserver("FileLog");

        // 3. Register the observers to the sensors
        temp.register(dashboard);
        temp.register(log);
        humidity.register(dashboard);
        humidity.register(log);

        // 4. Simulate sensor reading changes
        System.out.println("===== Simulating sensor readings =====");

        for (int i = 0; i < 10; i++) {
            System.out.println("--- Reading #" + (i + 1) + " ---");
            temp.setReading(20 + random.nextDouble() * 15);
            humidity.setReading(40 + random.nextDouble() * 20);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 5. Unregister test: logger stops receiving temperature updates
        System.out.println(
                "\n===== Unregistering logger from temperature sensor =====");
        temp.unregister(log);
        temp.setReading(25.0);

        // 6. Cloning test
        System.out.println("\n===== Cloning the temperature sensor =====");
        TemperatureSensor tempClone = temp.clone();
        tempClone.setName("Lab-Temp-Clone");

        System.out.println("Original : " + temp
                + " | observers = " + temp.getObserverCount());
        System.out.println("Clone    : " + tempClone
                + " | observers = " + tempClone.getObserverCount());

        System.out.println(
                "Changing the clone (no observers yet -> nothing printed):");
        tempClone.setReading(30.0);

        Observer cloneDashboard = new DashboardObserver("Clone");
        tempClone.register(cloneDashboard);

        System.out.println(
                "Changing the clone after registering its own dashboard:");
        tempClone.setReading(31.5);

        System.out.println(
                "Changing the original (only its own observers are notified):");
        temp.setReading(22.0);

        logger.info("Application finished.");
    }
}