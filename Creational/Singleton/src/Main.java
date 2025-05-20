import logging.Logger;


import static logging.LoggerType.*;

public class Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log(INFO, "Settings Updated");
        logger2.log(ERROR, "App Crashed");

        Runnable runnable1 = () -> {
            Logger logger3 = Logger.getInstance();
            logger3.log(FATAL, "FATAL ERROR");
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable1);
        Thread thread3 = new Thread(runnable1);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}