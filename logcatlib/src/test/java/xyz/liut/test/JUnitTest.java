package xyz.liut.test;


import org.junit.Test;

import xyz.liut.logcat.L;
import xyz.liut.logcat.LogLevel;
import xyz.liut.logcat.handler.StdHandler;

public class JUnitTest {

    @Test
    public void LogTest() {

        L
                .getDefault()
                .addHandler(new StdHandler())
                .setLevel(LogLevel.DEBUG);

        L.v("verbose");
        L.d("debug");
        L.i("info");
        L.w("warn");
        L.e("error");
        L.e("error test", new Throwable("err test"));
        L.wtf("wtf");
    }

}
