package xyz.liut.logcat.handler;

import org.junit.Test;

import xyz.liut.logcat.LogLevel;

/**
 * Create by liut on 20-9-22
 */
public class StdHandlerTest {

    @Test
    public void log() {
        StdHandler handler = new StdHandler();
        handler.log(LogLevel.DEBUG, "liut", "message");

        StdHandler handler2 = new StdHandler(false, false, false);
        handler2.log(LogLevel.DEBUG, "liut", "message2");
    }
}
