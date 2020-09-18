package xyz.liut.logcat;

import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;


/**
 * VERBOSE, DEBUG, INFO, WARN, ERROR, ASSERT
 * <p>
 * Create by liut on 2018/10/15 0015
 */
@SuppressWarnings({"unused"})
public class Logcat {

    /**
     * logHandler 集合
     */
    private final static Set<LogHandler> handlers = new HashSet<>(1);

    /**
     * log 级别
     */
    private static volatile LogLevel level = LogLevel.VERBOSE;

    /**
     * 获取 logHandler 列表
     *
     * @return 列表
     */
    public static Set<LogHandler> getHandlers() {
        return handlers;
    }


    /**
     * @return 旧的级别
     */
    public static LogLevel getLevel() {
        return level;
    }

    /**
     * 设置Log级别
     *
     * @param level 新级别
     */
    public static void setLevel(LogLevel level) {
        Logcat.level = level;
    }


// ==================================================================================================

    static void v(String tag, Object msg) {
        if (LogLevel.VERBOSE.compareTo(level) > -1)
            outputLogcat(LogLevel.VERBOSE, tag, msg, null);
    }

    static void d(String tag, Object msg) {
        if (LogLevel.DEBUG.compareTo(level) > -1)
            outputLogcat(LogLevel.DEBUG, tag, msg, null);
    }

    static void i(String tag, Object msg) {
        if (LogLevel.INFO.compareTo(level) > -1)
            outputLogcat(LogLevel.INFO, tag, msg, null);
    }

    static void w(String tag, Object msg) {
        if (LogLevel.WARN.compareTo(level) > -1)
            outputLogcat(LogLevel.WARN, tag, msg, null);
    }

    static void e(String tag, Object msg, Throwable e) {
        if (LogLevel.ERROR.compareTo(level) > -1)
            outputLogcat(LogLevel.ERROR, tag, msg, e);
    }

    static void a(String tag, Object msg) {
        if (LogLevel.ASSERT.compareTo(level) > -1)
            outputLogcat(LogLevel.ASSERT, tag, msg, null);
    }


    /**
     * 输出logcat到已注册的LogHandler
     *
     * @param logLevel log level
     * @param tag      log tag
     * @param msg      log content
     */
    private static void outputLogcat(LogLevel logLevel, @Nullable String tag, Object msg, Throwable e) {
        if (handlers.size() == 0) {
            System.out.println("当前没有 LogHandler " + msg);
            return;
        }

        if (tag == null) {
            tag = makeTag();
        }

        if (msg == null) {
            msg = "<null>";
        }

        String msgStr = msg.toString();

        if (e != null) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(os));
            msgStr = msg + "\n" + os.toString();
        }


        for (LogHandler handler : handlers) {
            handler.log(logLevel, tag, msgStr);
        }
    }

    /**
     * 根据当前线程方法栈制作log_tag
     * <p>
     * todo 把 TAG 抽象出来
     *
     * @return log tag
     */
    private static String makeTag() {
        String tag = "<none>";
        try {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            if (elements.length > 0) {
                Package pkg = Logcat.class.getPackage();
                String pkgName = pkg == null ? "<null>" : pkg.getName();
                boolean isAfterPkg = false;

                for (StackTraceElement element : elements) {
                    if (element.getClassName().contains(pkgName)) {
                        isAfterPkg = true;
                    } else {
                        if (isAfterPkg) {
                            tag = element.getFileName();
                            if (tag == null) {
                                tag = element.getClassName();
                                String[] str = tag.split("\\.");
                                if (str.length > 0) {
                                    tag = "<" + str[str.length - 1] + ">";
                                }
                            }
                            tag = tag + "_" + element.getLineNumber();
                            tag = tag.replace(".java", "");
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tag;
    }

}
