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

    // --------------------------VERBOSE------------------------------------------------------------

    public static void v(Object msg) {
        DEFAULT.println(LogLevel.VERBOSE, null, msg, null);
    }

    public static void v(String tag, Object msg) {
        DEFAULT.println(LogLevel.VERBOSE, tag, msg, null);
    }

    // --------------------------DEBUG--------------------------------------------------------------

    public static void d(Object msg) {
        DEFAULT.println(LogLevel.DEBUG, null, msg, null);
    }

    public static void d(String tag, Object msg) {
        DEFAULT.println(LogLevel.DEBUG, tag, msg, null);
    }

    // --------------------------INFO---------------------------------------------------------------

    public static void i(Object msg) {
        DEFAULT.println(LogLevel.INFO, null, msg, null);
    }

    public static void i(String tag, Object msg) {
        DEFAULT.println(LogLevel.INFO, tag, msg, null);
    }

    // ---------------------------WARN--------------------------------------------------------------

    public static void w(Object msg) {
        DEFAULT.println(LogLevel.WARN, null, msg, null);
    }

    public static void w(String tag, String msg) {
        DEFAULT.println(LogLevel.WARN, tag, msg, null);
    }

    public static void w(String tag, String msg, Throwable e) {
        DEFAULT.println(LogLevel.WARN, tag, msg, e);
    }

    public static void w(String msg, Throwable e) {
        DEFAULT.println(LogLevel.WARN, null, msg, e);
    }

    // -------------------------ERROR---------------------------------------------------------------

    public static void e(Object msg) {
        DEFAULT.println(LogLevel.ERROR, null, msg, null);
    }

    public static void e(String tag, String msg) {
        DEFAULT.println(LogLevel.ERROR, tag, msg, null);
    }

    public static void e(String tag, String msg, Throwable e) {
        DEFAULT.println(LogLevel.ERROR, tag, msg, e);
    }

    public static void e(String msg, Throwable e) {
        DEFAULT.println(LogLevel.ERROR, null, msg, e);
    }

    // ------------------------ASSERT---------------------------------------------------------------

    public static void wtf(Throwable e) {
        DEFAULT.println(LogLevel.ASSERT, null, null, e);
    }

    public static void wtf(String msg) {
        DEFAULT.println(LogLevel.ASSERT, null, msg, new Throwable("wtf"));
    }

    public static void wtf(String tag, String msg) {
        DEFAULT.println(LogLevel.ASSERT, tag, msg, new Throwable("wtf"));
    }

    public static void wtf(String tag, String msg, Throwable e) {
        DEFAULT.println(LogLevel.ASSERT, tag, msg, e);
    }

    public static void wtf(String msg, Throwable e) {
        DEFAULT.println(LogLevel.ASSERT, null, msg, e);
    }


}
