package xyz.liut.logcat;


/**
 * log 采集
 * <p>
 * Create by liut on 2018/10/15 0015
 */
public final class L {

    private static final Logcat DEFAULT = new Logcat();

    public static Logcat getDefault() {
        return DEFAULT;
    }

    public static void v(Object msg) {
        DEFAULT.println(LogLevel.VERBOSE, null, msg, null);
    }

    public static void v(String tag, Object msg) {
        DEFAULT.println(LogLevel.VERBOSE, tag, msg, null);
    }

    public static void d(Object msg) {
        DEFAULT.println(LogLevel.DEBUG, null, msg, null);
    }

    public static void d(String tag, Object msg) {
        DEFAULT.println(LogLevel.DEBUG, tag, msg, null);
    }


    public static void i(Object msg) {
        DEFAULT.println(LogLevel.INFO, null, msg, null);
    }

    public static void i(String tag, Object msg) {
        DEFAULT.println(LogLevel.INFO, tag, msg, null);
    }


    public static void w(Object msg) {
        DEFAULT.println(LogLevel.WARN, null, msg, null);
    }

    public static void w(String tag, Object msg) {
        DEFAULT.println(LogLevel.WARN, tag, msg, null);
    }


    public static void e(Object msg) {
        DEFAULT.println(LogLevel.ERROR, null, msg, null);
    }

    public static void e(Object msg, Throwable e) {
        DEFAULT.println(LogLevel.ERROR, null, msg, e);
    }


    public static void wtf(Object msg) {
        DEFAULT.println(LogLevel.ASSERT, null, msg, new Throwable("wtf"));
    }

    public static void wtf(Object msg, Throwable e) {
        DEFAULT.println(LogLevel.ASSERT, null, msg, e);
    }


}
