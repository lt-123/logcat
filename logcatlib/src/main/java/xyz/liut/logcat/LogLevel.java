package xyz.liut.logcat;

/**
 * log level
 */
public enum LogLevel {

    /**
     * Priority constant for the println method; use L.v.
     */
    VERBOSE,

    /**
     * Priority constant for the println method; use Log.d.
     */
    DEBUG,

    /**
     * Priority constant for the println method; use L.i.
     */
    INFO,

    /**
     * Priority constant for the println method; use L.w.
     */
    WARN,

    /**
     * Priority constant for the println method; use L.e.
     */
    ERROR,

    /**
     * Priority constant for the println method.
     */
    ASSERT,


    /**
     * no log
     */
    NO;

    /**
     * 获取简称
     */
    public String getShortName() {
        switch (this) {
            case VERBOSE:
                return "V";
            case DEBUG:
                return "D";
            case INFO:
                return "I";
            case WARN:
                return "W";
            case ERROR:
                return "E";
            case ASSERT:
                return "A";
            case NO:
                return "N";
            default:
                return "none";
        }
    }

}
