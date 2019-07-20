package xyz.liut.logcat;


/**
 * log 采集
 * <p>
 * Create by liut on 2018/10/15 0015
 */
@SuppressWarnings("unused")
public final class L {

    public static void v(Object msg) {
        Logcat.v(msg);
    }

    public static void v(String tag, Object msg) {
        Logcat.v(tag, msg);
    }

    public static void d(Object msg) {
        Logcat.d(msg);
    }

    public static void d(String tag, Object msg) {
        Logcat.d(tag, msg);
    }


    public static void i(Object msg) {
        Logcat.i(msg);
    }

    public static void i(String tag, Object msg) {
        Logcat.i(tag, msg);
    }


    public static void w(Object msg) {
        Logcat.w(msg);
    }

    public static void w(String tag, Object msg) {
        Logcat.w(tag, msg);
    }


    public static void e(Object msg) {
        Logcat.e(msg);
    }

    public static void e(String tag, Object msg) {
        Logcat.e(tag, msg);
    }

    public static void e(Object msg, Throwable e) {
        Logcat.e(msg, e);
    }

    public static void e(String tag, Object msg, Throwable e) {
        Logcat.e(tag, msg, e);
    }


    public static void a(Object msg) {
        Logcat.a(msg);
    }

    public static void a(String tag, Object msg) {
        Logcat.a(tag, msg);
    }


}
