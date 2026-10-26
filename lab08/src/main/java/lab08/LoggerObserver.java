package lab08;
//وظيفته: printf تطبع في الـConsole، وlogger.info تسجل المعلومات حسب إعدادات الـLogging.
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerObserver extends SensorObserver {

    private static final Logger logger =
            LoggerFactory.getLogger(LoggerObserver.class);

    public LoggerObserver(String name) {
        super(name);
    }

    @Override
    protected void display(Sensor sensor) {
        System.out.printf("[LOGGER    %s] %s%n", getName(), sensor);
        logger.info("{} -> {}", getName(), sensor);
    }
}