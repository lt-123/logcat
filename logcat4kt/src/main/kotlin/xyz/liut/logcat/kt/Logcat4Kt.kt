@file:Suppress("NOTHING_TO_INLINE")

package xyz.liut.logcat.kt

import xyz.liut.logcat.L

inline fun Any?.logVerbose(tag: String? = null) {
    L.v(tag, this)
}

inline fun Any?.logDebug(tag: String? = null) {
    L.d(tag, this)
}

inline fun Any?.logInfo(tag: String? = null) {
    L.i(tag, this)
}

inline fun Any?.logWarn(tag: String? = null) {
    L.w(tag, this)
}

inline fun Any?.logError(tag: String? = null, throwable: Throwable? = null) {
    L.e(tag, this, throwable)
}

