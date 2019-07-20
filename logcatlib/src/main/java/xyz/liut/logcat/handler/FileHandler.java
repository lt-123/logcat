package xyz.liut.logcat.handler;


import org.jetbrains.annotations.Nullable;
import xyz.liut.logcat.LogHandler;
import xyz.liut.logcat.LogLevel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 输出到文件
 * <p>
 * Create by liut on 2018/10/15 0015
 */
@Deprecated
public class FileHandler implements LogHandler, Runnable {


    // -----------------------------------config begin----------------------------------------------

    /**
     * log文件命名格式
     */
    private static final String LOG_FILE_NAME_PATTERN = "'KindleLaw_'yyyy-MM-dd'.log'";

    /**
     * 线程存活时间 3分钟
     */
    private static final long THREAD_ALIVE_TIME = 1000 * 60 * 3;

    /**
     * 触发检查文件是否过期时间间隔 1天
     */
    private static final long CHECK_FILES_INTERVAL = 1000 * 60 * 60 * 24;

    /**
     * 保存日志的文件夹
     *
     * @return 文件夹
     */
    private static File logFileDir() {
        return new File("./logcat");
    }


    // -----------------------------------config end------------------------------------------------

    private final ReentrantLock lock;
    private final Condition condition;

    /**
     * 日志缓存
     */
    private volatile LinkedList<String> texts;

    /**
     * 保存的路径， 文件
     */
    private volatile File dir, file;


    private Date createFileDate;

    /**
     * 文件名格式
     */
    private SimpleDateFormat fileFormat;

    /**
     * IO线程
     */
    private volatile Thread thread;

    FileHandler() {
        fileFormat = new SimpleDateFormat(LOG_FILE_NAME_PATTERN, Locale.CHINESE);
        lock = new ReentrantLock();
        condition = lock.newCondition();
        texts = new LinkedList<>();
        initFile();
    }

    /**
     * 初始化文件对象
     */
    private void initFile() {
        try {
            dir = logFileDir();
            if (dir != null) {
                String logFileName = fileFormat.format(new Date());
                file = new File(dir + File.separator + logFileName);
                createFileDate = new Date();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 新的Log
     *
     * @param level -
     * @param tag   -
     * @param msg   -
     */
    @Override
    public void log(LogLevel level, @Nullable String tag, @Nullable String msg) {

        // VERBOSE 级别不保存
        if (level == LogLevel.VERBOSE) {
            return;
        }

        // 检查文件是否就绪， 以及日期是否发生改变
        if (file == null || createFileDate.before(new Date())) {
            initFile();
            if (file == null) {
                return;
            }
        }

        String lv = "";
        switch (level) {
            case ASSERT:
                lv = "ASSERT/";
                break;
            case DEBUG:
                lv = "D/";
                break;
            case ERROR:
                lv = "E/";
                break;
            case INFO:
                lv = "I/";
                break;
            case VERBOSE:
                lv = "V/";
                break;
            case WARN:
                lv = "W/";
                break;
        }

        // 检查线程
        if (thread == null) {
            thread = new Thread(this);
            thread.setName("LOG保存到文件线程");
            thread.start();
        }

        // 放入数据， 并唤醒线程
        lock.lock();
        try {
            texts.add(formatTime(new Date()) + " " + lv + tag + ": " + msg + "\n");
            condition.signalAll();
            println("signalAll!");
        } finally {
            lock.unlock();
        }
    }


    @Override
    public void run() {
        System.out.println("LOG线程启动");
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file, true);
            for (; ; ) {
                boolean bySignal;

                lock.lock();
                try {
                    while (texts.size() > 0) {
                        String msg = texts.pollFirst();
                        byte[] bytes = msg.getBytes("UTF-8");

                        // 简易的XOR加密
                        for (int i = 0; i < bytes.length; i++) {
                            bytes[i] = (byte) (bytes[i] ^ 0b11011011);
                        }

                        outputStream.write(bytes);
                    }

                    bySignal = condition.await(THREAD_ALIVE_TIME, TimeUnit.MILLISECONDS);
                } finally {
                    lock.unlock();
                }


                checkFiles();
                if (!bySignal) {
                    System.out.println("等待超时， LOG线程结束");
                    break;
                } else {
                    println("bySignal");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (lock.isLocked()) {
                lock.unlock();
            }
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 置空
        thread = null;
    }

    private long time;

    /**
     * 检查日志文件是否过期， 7天， 过期删除
     */
    private void checkFiles() {
        long interval = System.currentTimeMillis() - time;
        if (interval > CHECK_FILES_INTERVAL) {
            if (dir != null && dir.exists()) {
                File logFiles[] = dir.listFiles();
                if (logFiles != null) {
                    for (File f : logFiles) {
                        try {
                            Date date = fileFormat.parse(f.getName());
                            Calendar calendar = GregorianCalendar.getInstance();
                            calendar.add(Calendar.WEEK_OF_YEAR, -1);
                            if (date.before(calendar.getTime())) {
                                f.delete();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                            f.delete();
                        }
                    }
                }
                time = new Date().getTime();
            }
        }


    }


    private String formatTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS", Locale.CHINESE);
        return format.format(date);
    }

    private void println(String msg) {
//        System.out.println(msg);
    }


}
