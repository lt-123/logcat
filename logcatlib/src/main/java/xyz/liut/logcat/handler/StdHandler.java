package xyz.liut.logcat.handler;

import xyz.liut.logcat.LogHandler;
import xyz.liut.logcat.LogLevel;

import static xyz.liut.logcat.LogLevel.ERROR;

/**
 * System.out
 * System.err
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class StdHandler implements LogHandler {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    @Override
    public void log(LogLevel level, String tag, String msg) {

        String log = tag + ": " + msg;

        switch (level) {
            case VERBOSE:
                break;
            case DEBUG:
                log = ANSI_GREEN + log;
                break;
            case INFO:
                log = ANSI_BLUE + log;
                break;
            case WARN:
                log = ANSI_YELLOW + log;
                break;
//            case ERROR:
//                break;
//            case ASSERT:
//                break;
        }

        if (level == ERROR) {
            System.err.println(log);
        } else {
            System.out.println(log);
        }

//        System.out.print(ANSI_RED);

    }
}
