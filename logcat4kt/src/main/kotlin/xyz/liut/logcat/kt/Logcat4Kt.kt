@file:Suppress("NOTHING_TO_INLINE")

package xyz.liut.logcat.kt

import xyz.liut.logcat.L
import xyz.liut.logcat.LogLevel


inline fun logVerbose(msg: Any?, tag: String? = null) {
    L.v(tag, msg)
}

inline fun logDebug(msg: Any?, tag: String? = null) {
    L.d(tag, msg)
}

inline fun logInfo(msg: Any?, tag: String? = null) {
    L.i(tag, msg)
}

inline fun logWarn(msg: Any?, tag: String? = null, throwable: Throwable? = null) {
    L.w(tag, msg?.toString(), throwable)
}

inline fun logError(msg: Any?, tag: String? = null, throwable: Throwable? = null) {
    L.e(tag, msg?.toString(), throwable)
}

inline fun logWtf(msg: Any?, tag: String? = null, throwable: Throwable? = null) {
    if (throwable != null) {
        L.wtf(tag, msg?.toString(), throwable)

    } else {
        L.wtf(tag, msg?.toString(), Exception(msg?.toString()))
    }
}

inline fun Throwable.printLogcat(
    msg: Any? = null,
    tag: String? = null,
    level: LogLevel = LogLevel.WARN
) {
    L.getDefault().println(level, tag, msg ?: "", this)
}

