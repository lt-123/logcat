package xyz.liut.logcat.android;

import android.text.TextUtils;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import xyz.liut.logcat.LogHandler;
import xyz.liut.logcat.LogLevel;

/**
 * android logcat
 * <p>
 * Create by liut on 2018/10/15 0015
 */
public class AndroidLogcatHandler implements LogHandler {

    private final static int LOG_MAX_LENGTH = 3 * 1024;

    private final String BASE_TAG;

    /**
     * @param prefix tag 前缀
     */
    public AndroidLogcatHandler(String prefix) {
        BASE_TAG = prefix;
    }


    @Override
    public void log(LogLevel level, @NotNull String tag, @NotNull String msg) {
        if (!TextUtils.isEmpty(BASE_TAG)) {
            tag = BASE_TAG + tag;
        }

        switch (level) {
            case VERBOSE:
                println(Log.VERBOSE, tag, msg);
                break;
            case DEBUG:
                println(Log.DEBUG, tag, msg);
                break;
            case INFO:
                println(Log.INFO, tag, msg);
                break;
            case WARN:
                println(Log.WARN, tag, msg);
                break;
            case ERROR:
                println(Log.ERROR, tag, msg);
                break;
            case ASSERT:
                println(Log.ASSERT, tag, msg);
                break;
            default:
                break;
        }

    }

    public static void println(int priority, String tag, String msg) {
        long length = msg.length();

        if (length > LOG_MAX_LENGTH) {
            // 循环分段打印日志
            while (msg.length() > LOG_MAX_LENGTH) {
                String logContent = msg.substring(0, LOG_MAX_LENGTH);
                Log.println(priority, tag, logContent);

                msg = msg.substring(LOG_MAX_LENGTH);
            }
        }
        Log.println(priority, tag, msg);
    }

}

