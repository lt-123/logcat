package xyz.liut.logcat;

import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;


/**
 * VERBOSE, DEBUG, INFO, WARN, ERROR, ASSERT
 * <p>
 * Create by liut on 2018/10/15 0015
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Logcat {

    private final static Set<LogHandler> handlers = new HashSet<>(1);

    public static Set<LogHandler> getHandlers() {
        return handlers;
    }

    public static void v(Object msg) {
        v(null, msg);
    }

    public static void v(String tag, Object msg) {
        outputLogcat(LogLevel.VERBOSE, tag, msg, null);
    }


    public static void d(Object msg) {
        d(null, msg);
    }

    public static void d(String tag, Object msg) {
        outputLogcat(LogLevel.DEBUG, tag, msg, null);
    }


    public static void i(Object msg) {
        i(null, msg);
    }

    public static void i(String tag, Object msg) {
        outputLogcat(LogLevel.INFO, tag, msg, null);
    }


    public static void w(Object msg) {
        w(null, msg);
    }

    public static void w(String tag, Object msg) {
        outputLogcat(LogLevel.WARN, tag, msg, null);
    }


    public static void e(Object msg) {
        e(null, msg);
    }

    public static void e(String tag, Object msg) {
        outputLogcat(LogLevel.ERROR, tag, msg, null);
    }

    public static void e(Object msg, Throwable e) {
        outputLogcat(LogLevel.ERROR, null, msg, e);
    }

    public static void e(String tag, Object msg, Throwable e) {
        outputLogcat(LogLevel.ERROR, tag, msg, e);
    }


    public static void a(Object msg) {
        a(null, msg);
    }

    public static void a(String tag, Object msg) {
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
        }

        if (tag == null) {
            tag = makeTag();
        }

        if (msg == null) {
            msg = "<null>";
        }
        if (e != null) {
            StackTraceElement[] elements = e.getStackTrace();
            if (elements.length > 0) {
                StringBuilder builder = new StringBuilder();
                builder
                        .append("\n").append(msg).append("\n")
                        .append(e.getClass().getName()).append(": ").append(e.getMessage()).append("\n");
                for (StackTraceElement element : elements) {
                    builder.append("\tat ").append(element).append("\n");
                }
                msg = builder.toString();
            }
        }
        for (LogHandler handler : handlers) {
            handler.log(logLevel, tag, msg.toString());
        }
    }

    /**
     * 根据当前线程方法栈制作log_tag
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
