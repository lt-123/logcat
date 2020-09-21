package xyz.liut.logcat.handler;

import org.jetbrains.annotations.NotNull;

import xyz.liut.logcat.LogHandler;
import xyz.liut.logcat.LogLevel;

import static xyz.liut.logcat.LogLevel.ERROR;

/**
 * 使用标准输出、标准错误输出打印Log
 * <p>
 * verbose：默认颜色
 * debug：绿色
 * info：蓝色
 * warn：黄色
 * error：红色
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class StdHandler implements LogHandler {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * 是否使用 标准错误输出 打野 Error 级别 Log
     */
    private boolean useStdErr;

    /**
     * 是否显示 TAG
     */
    private boolean showTag;

    public StdHandler() {
        this(false, true);
    }

    /**
     * @param useStdErr 是否使用 标准错误输出 打野 Error 级别 Log
     * @param showTag   是否显示 tag
     */
    public StdHandler(boolean useStdErr, boolean showTag) {
        this.useStdErr = useStdErr;
        this.showTag = showTag;
    }

    @Override
    public void log(LogLevel level, @NotNull String tag, @NotNull String msg) {
        String log;
        if (showTag)
            log = tag + ": " + msg;
        else
            log = msg;

        if (useStdErr && level == ERROR) {
            // 使用 err 打印
            System.err.println(log);
        } else {
            // 配置颜色
            switch (level) {
                case VERBOSE:
                    break;
                case DEBUG:
                    log = ANSI_GREEN + log;
                    break;
                case INFO:
                    log = ANSI_BLUE + log;
                    break;
                case WARN:
                    log = ANSI_YELLOW + log;
                    break;
                case ERROR:
                case ASSERT:
                    log = ANSI_RED + log;
                    break;
            }

            // 打印 log
            System.out.println(log + ANSI_RESET);
        }


    }
}
