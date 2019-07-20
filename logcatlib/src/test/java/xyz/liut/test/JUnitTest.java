package xyz.liut.test;


import org.junit.Test;
import xyz.liut.logcat.L;
import xyz.liut.logcat.Logcat;
import xyz.liut.logcat.handler.StdHandler;

public class JUnitTest {

    @Test
    public void LogTest(){
        Logcat.getHandlers().add(new StdHandler());
        L.v("hahahha");
        L.d("hahahha");
        L.i("hahahha");
        L.w("hahahha");
        L.e("hahahha");
    }

}
