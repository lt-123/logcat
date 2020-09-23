package xyz.liut.logcat;

import org.junit.Before;
import org.junit.Test;

import xyz.liut.logcat.handler.StdHandler;

/**
 * Create by liut on 20-9-22
 */
public class LTest {

    @Before
    public void setUp() {
        L
                .getDefault()
                .addHandler(new StdHandler())
                .setLevel(LogLevel.DEBUG);
    }

    @Test
    public void LogTest() {
        L.v("verbose");
        L.v("tag", "verbose");

        L.d("debug");
        L.d("tag", "debug");

        L.i("info");
        L.i("tag", "info");

        L.w("warn");
        L.w("tag", "warn");
        L.w("tag", "warn", new Throwable("warn test"));
        L.w("warn", new Throwable("warn test"));

        L.e("error");
        L.e("tag", "error");
        L.e("tag", "error", new Throwable("error test"));
        L.e("warn", new Throwable("error test"));

        L.wtf("wtf");
        L.wtf("tag", "wtf");
        L.wtf("tag", "wtf", new Throwable("wtf test"));
        L.wtf("wtf", new Throwable("wtf test"));
        L.wtf(new Throwable("wtf test"));
    }

}
