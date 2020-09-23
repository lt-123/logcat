package xyz.liut.logcat.handler;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import xyz.liut.logcat.LogHandler;
import xyz.liut.logcat.LogLevel;

/**
 * 带有基于时间回滚功能的 FileHandler
 * 会自动删除超过 1 周的日志文件
 * <p>
 * Create by liut on 20-9-21
 */
public class RollbackFileHandler implements LogHandler {

    /**
     * 触发检查文件是否过期时间间隔 1天
     */
    private static final long CHECK_FILES_INTERVAL = 1000 * 60 * 60 * 24;

    /**
     * 装饰者模式
     */
    private FileHandler fileHandler;

    /**
     * 文件名格式化工具
     */
    private SimpleDateFormat format;

    /**
     * 今天0点的时间
     */
    private long today;

    /**
     * 目标文件夹
     */
    private String dir;

    /**
     * 存留时间, 周数
     */
    private int week;

    /**
     * @param dir             目标文件夹
     * @param week            日志存留时间, 单位: 周, 为正整数
     * @param fileNamePattern 输出文件命名格式, 如 "yyyy-MM-dd-HH-mm-ss'.log'"
     */
    public RollbackFileHandler(@NotNull String dir, int week, @NotNull String fileNamePattern) {
        this.dir = dir;
        this.week = week;

        format = new SimpleDateFormat(fileNamePattern, Locale.CHINA);
        // 生成 fileHandler
        fileHandler = new FileHandler(new File(dir, format.format(new Date())).toString());
        today = getToday();
    }

    @Override
    public void log(LogLevel level, @NotNull String tag, @NotNull String msg) {
        roll();
        fileHandler.log(level, tag, msg);
    }

    /**
     * 检查日志文件是否过期， 7天， 过期删除
     */
    private void roll() {
        long interval = System.currentTimeMillis() - today;
        // 判断是否已过夜(不是很精准, 无碍)
        if (interval >= CHECK_FILES_INTERVAL) {
            // 更新 输出文件路径
            fileHandler.updateFile(new File(dir, format.format(new Date())).toString());

            // 检查过期文件
            checkDir();
        }
    }

    /**
     * 检查过期文件
     */
    private void checkDir() {
        File dirFile = new File(dir);
        if (dirFile.exists()) {
            File[] logFiles = dirFile.listFiles();
            if (logFiles != null) {
                for (File f : logFiles) {
                    try {
                        Date date = format.parse(f.getName());
                        Calendar calendar = GregorianCalendar.getInstance();
                        calendar.add(Calendar.WEEK_OF_YEAR, -week);
                        // 删除过期文件
                        if (date.before(calendar.getTime())) {
                            //noinspection ResultOfMethodCallIgnored
                            f.delete();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        // 删除不符合命名规则的文件
                        //noinspection ResultOfMethodCallIgnored
                        f.delete();
                    }
                }
            }
            // 刷新今日时间
            today = getToday();
        }
    }

    /**
     * @return 当天时间的0点
     */
    private long getToday() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

}

