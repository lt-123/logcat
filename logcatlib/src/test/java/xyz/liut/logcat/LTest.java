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
        L.d("debug");
        L.i("info");
        L.w("warn");
        L.e("error");
        L.e("error test", new Throwable("err test"));
        L.wtf("wtf");
    }

}
