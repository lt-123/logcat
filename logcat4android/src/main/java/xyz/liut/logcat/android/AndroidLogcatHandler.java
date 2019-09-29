package xyz.liut.logcat.android;

import android.util.Log;
import org.jetbrains.annotations.NotNull;
import xyz.liut.logcat.LogHandler;
import xyz.liut.logcat.LogLevel;
import xyz.liut.logcat4android.BuildConfig;

/**
 * android logcat
 * <p>
 * Create by liut on 2018/10/15 0015
 */
public class AndroidLogcatHandler implements LogHandler {

    private final String BASE_TAG;

    private final boolean NEED_TAG = !BuildConfig.DEBUG;

    /**
     * @param baseTag tag 前缀
     */
    public AndroidLogcatHandler(String baseTag) {
        BASE_TAG = baseTag;
    }


    @Override
    public void log(LogLevel level, @NotNull String tag, @NotNull String msg) {
        if (NEED_TAG) {
            tag = BASE_TAG + tag;
        }

        switch (level) {
            case ASSERT:
//                Log.d(tag, msg);
                break;
            case DEBUG:
                Log.d(tag, msg);
                break;
            case ERROR:
                Log.e(tag, msg);
                break;
            case INFO:
                Log.i(tag, msg);
                break;
            case VERBOSE:
                Log.v(tag, msg);
                break;
            case WARN:
                Log.w(tag, msg);
                break;
            default:
                break;
        }

    }

}
