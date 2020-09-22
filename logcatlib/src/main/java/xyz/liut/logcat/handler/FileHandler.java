package xyz.liut.logcat.handler;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import xyz.liut.logcat.LogHandler;
import xyz.liut.logcat.LogLevel;

/**
 * 把日志输出到文件
 * <p>
 * Create by liut on 2018/10/15 0015
 */
public class FileHandler implements LogHandler {

    /**
     * 线程存活时间 2分钟
     */
    private static final long THREAD_ALIVE_TIME = 1000 * 60 * 2;

    /**
     * log 队列
     */
    private BlockingQueue<String> texts;

    private PrintRunnable runnable;

    private SimpleDateFormat format;

    public FileHandler(String file) {
        texts = new LinkedBlockingQueue<>();
        runnable = new FileHandler.PrintRunnable(texts, file);
        format = new SimpleDateFormat("HH:mm:ss.SSS", Locale.CHINESE);
    }

    /**
     * 更新输出文件
     *
     * @param file 新的输出文件
     */
    public void updateFile(String file) {
        runnable.file = file;
    }

    @Override
    public void log(LogLevel level, @NotNull String tag, @NotNull String msg) {
        String lv = level.getShortName() + "/";
        texts.add(format.format(new Date()) + " " + lv + tag + ": " + msg + "\n");
        startThread();
    }

    private void startThread() {
        if (!runnable.running) {
            runnable.running = true;
            new Thread(runnable).start();
        }
    }


    private static class PrintRunnable implements Runnable {

        private BlockingQueue<String> texts;
        private volatile String file;

        private volatile boolean running;

        public PrintRunnable(BlockingQueue<String> texts, String file) {
            this.texts = texts;
            this.file = file;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start.");
            for (; ; ) {
                File f = new File(file);
                if (!f.getParentFile().exists()) {
                    boolean ret = f.getParentFile().mkdirs();
                    if (!ret) {
                        System.err.println("创建文件夹失败: " + f);
                    }
                }

                if (f.isDirectory()) {
                    boolean ret = f.delete();
                    if (!ret) {
                        System.err.println("删除文件夹失败: " + f);
                    }
                }

                try (FileOutputStream fos = new FileOutputStream(file, true)) {
                    String line = texts.poll(THREAD_ALIVE_TIME, TimeUnit.MINUTES);
                    if (line != null) {
                        fos.write(line.getBytes());
                        fos.flush();
                    } else {
                        running = false;
                        System.out.println("end.");
                        break;
                    }
                } catch (IOException | InterruptedException e) {
                    running = false;
                    e.printStackTrace();
                    break;
                }
            }

            System.out.println(Thread.currentThread().getName() + " stop.");
        }

    }


}
