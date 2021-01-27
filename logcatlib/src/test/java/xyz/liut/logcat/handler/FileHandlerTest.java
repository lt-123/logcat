package xyz.liut.logcat.handler;

import org.junit.Test;

import xyz.liut.logcat.LogLevel;

/**
 * Create by liut on 20-9-21
 */
public class FileHandlerTest {

    @Test
    public void log() throws InterruptedException {
        FileHandler handler = new FileHandler("tmp/logcat.txt");

        for (int i = 0; i < 100; i++) {
            handler.log(LogLevel.DEBUG, "liut", "hello " + i);
        }

        Thread.sleep(1000 * 5);

        handler.log(LogLevel.DEBUG, "liut", "====================");

        Thread.sleep(1000 * 10);
    }

}
