package logging;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static volatile Logger instance;

    private Logger() {}

    public static Logger getInstance() {
        Logger logger = instance;
        if (logger == null) {
            synchronized (Logger.class) {
                logger = instance;
                if (logger == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }


    public void log(LoggerType type, String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String output = String.format("[%s] [%s] %s", timestamp, type, message);
        System.out.println(output);
    }
}

//*******************************************//

// lw mn 8er thread safe hy7sl case :
// Thread A and Thread B wanna run at the same time, fa y3mlo 2 loggers
// BOOM , singleton is broken LOL

// w el 7l ?

// if (instance == null) {
//    instance = new Logger(...);
//}

// tftkr kda 5las ? et3sht? FU** NO

//synchronized(Logger.class) {
//    if (instance == null) {
//        instance = new Logger(...);
//    }
//}

// y3nee 45al one thread can create the instance, safe but slow AF cuz of Locks and unlocks technique
// that used to prevent 2 threads from running that block at the same time

//Locking/unlocking takes a little CPU time, makes it slow fsh5 (m4 awe)

// 7lha (DCL) Double-Checking Locking lazm el 2wl y3ml first check of not locking

// if (created) no-locking the inside check for the purose of not 2 threads run the block same time

// without volatile ->> Possible to see half made instance

// 1. Allocate memory for Logger                       4. Check instance != null  ---> true
//2. Initialize Logger (fully done)                   5. Use fully initialized instance safely
//3. Set instance reference (volatile ensures steps 1,2 done before 3)

// mn el a5r :
// volatile is ordering of writes and reads
//Thread <A> writes to a volatile variable, Thread B see that write fully completed and in order

// and (logger == null) instead of (instance == null) outperform 40%

// leh ??

// Reading a local variable is faster than reading a static field
// reading a static field (be3ml more mem lookup)
// w local var stored in stack memo or cpu registers, fast to access 3la 3ks eltanya read from Main memo directly



