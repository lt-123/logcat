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
public class Logcat {

    /**
     * logHandler 集合
     */
    private final Set<LogHandler> handlers = new HashSet<>(2);

    /**
     * log 级别
     */
    private volatile LogLevel level = LogLevel.VERBOSE;

    /**
     * 添加 LogHandler
     */
    public Logcat addHandler(LogHandler handler) {
        handlers.add(handler);
        return this;
    }

    /**
     * 去除 LogHandler
     */
    public Logcat removeHandler(LogHandler handler) {
        handlers.remove(handler);
        return this;
    }

    /**
     * 清空 LogHandler
     */
    public Logcat clearHandler() {
        handlers.clear();
        return this;
    }


    /**
     * @return 旧的级别
     */
    public LogLevel getLevel() {
        return level;
    }

    /**
     * 设置Log级别
     *
     * @param level 新级别
     */
    public Logcat setLevel(LogLevel level) {
        this.level = level;
        return this;
    }


// ==================================================================================================

    /**
     * 输出 logcat
     *
     * @param level 等级
     * @param tag   tag
     * @param msg   msg
     * @param e     exception
     */
    public void println(LogLevel level, String tag, Object msg, Throwable e) {
        if (level == null) {
            throw new NullPointerException("level 不可为空");
        }
        if (level.compareTo(this.level) > -1) {
            outputLogcat(level, tag, msg, e);
        }
    }

    /**
     * 输出logcat到已注册的LogHandler
     *
     * @param logLevel log level
     * @param tag      log tag
     * @param msg      log content
     */
    private void outputLogcat(LogLevel logLevel, @Nullable String tag, Object msg, Throwable e) {
        if (handlers.size() == 0) {
            System.err.println("No LogHandler " + msg);
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
     *
     * @return log tag
     */
    private String makeTag() {
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
                            tag = tag + "#" + element.getLineNumber();
                            tag = tag.replace(".java", "");
                            tag = tag.replace(".kt", "");
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
