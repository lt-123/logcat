package xyz.liut.logcat;


import org.jetbrains.annotations.Nullable;

/**
 * log 处理
 * <p>
 * Create by liut on 2018/10/15 0015
 */
public interface LogHandler {

    /**
     * @param level log 等级
     * @param tag   tag
     * @param msg   msg
     */
    void log(LogLevel level, @Nullable String tag, @Nullable String msg);

}
