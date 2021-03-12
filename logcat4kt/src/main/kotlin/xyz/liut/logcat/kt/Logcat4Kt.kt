package xyz.liut.logcat.kt

import xyz.liut.logcat.L
import xyz.liut.logcat.LogLevel
import xyz.liut.logcat.Logcat

/**
 * verbose 级别日志输出
 *
 * @param logcat 输出到的 logcat
 * @param tag 日志标签
 * @param msgBlock 日志体
 */
inline fun logVerbose(tag: String? = null, logcat: Logcat? = null, msgBlock: () -> Any?) {
    printlnLogcat(logcat, LogLevel.VERBOSE, tag, msgBlock.invoke(), null)
}

/**
 * info
 *
 * @param logcat 输出到的 logcat
 * @param tag 日志标签
 * @param msgBlock 日志体
 */
inline fun logDebug(tag: String? = null, logcat: Logcat? = null, msgBlock: () -> Any?) {
    printlnLogcat(logcat, LogLevel.DEBUG, tag, msgBlock.invoke(), null)
}

/**
 * info
 *
 * @param logcat 输出到的 logcat
 * @param tag 日志标签
 * @param msgBlock 日志体
 */
inline fun logInfo(tag: String? = null, logcat: Logcat? = null, msgBlock: () -> Any?) {
    printlnLogcat(logcat, LogLevel.INFO, tag, msgBlock.invoke(), null)
}

/**
 * warn
 *
 * @param logcat 输出到的 logcat
 * @param tag 日志标签
 * @param msgBlock 日志体
 * @param throwable 异常
 */
inline fun logWarn(
    tag: String? = null,
    logcat: Logcat? = null,
    throwable: Throwable? = null,
    msgBlock: () -> Any?
) {
    printlnLogcat(logcat, LogLevel.WARN, tag, msgBlock.invoke(), throwable)
}

/**
 * error
 *
 * @param logcat 输出到的 logcat
 * @param tag 日志标签
 * @param msgBlock 日志体
 * @param throwable 异常
 */
inline fun logError(
    tag: String? = null,
    throwable: Throwable? = null,
    logcat: Logcat? = null,
    msgBlock: () -> Any?
) {
    printlnLogcat(logcat, LogLevel.ERROR, tag, msgBlock.invoke(), throwable)
}

/**
 * assert
 *
 * @param logcat 输出到的 logcat
 * @param tag 日志标签
 * @param msgBlock 日志体
 * @param throwable 异常, 为空时会自动生成一个
 */
inline fun logWtf(
    tag: String? = null,
    throwable: Throwable? = null,
    logcat: Logcat? = null,
    msgBlock: () -> Any?
) {
    val msg = msgBlock.invoke()?.toString()
    val e = throwable ?: Throwable(msg)
    printlnLogcat(logcat, LogLevel.ASSERT, tag, msg, e)
}

/**
 * 打印 throwable
 */
fun Throwable.printLogcat(
    tag: String? = null,
    logcat: Logcat? = null,
    level: LogLevel = LogLevel.WARN,
    msgBlock: (() -> Any?)? = null
) {
    printlnLogcat(logcat, level, tag, msgBlock?.invoke()?.toString() ?: message, this)
}

/**
 * 日志输出
 *
 * @param logcat 输出到的 logcat
 * @param level 日志等级
 * @param tag 日志标签
 * @param msg 日志体
 * @param throwable 异常
 */
@Suppress("NOTHING_TO_INLINE")
inline fun printlnLogcat(
    logcat: Logcat?,
    level: LogLevel?,
    tag: String?,
    msg: Any?,
    throwable: Throwable?
) {
    (logcat ?: L.getDefault()).println(level, tag, msg, throwable)
}
