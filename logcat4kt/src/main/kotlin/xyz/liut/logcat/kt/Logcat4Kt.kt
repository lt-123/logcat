@file:Suppress("NOTHING_TO_INLINE")

package xyz.liut.logcat.kt

import xyz.liut.logcat.L

inline fun Any?.logVerbose(msg: String? = null) {
    if (msg == null) {
        L.v(this)
    } else {
        L.v("$msg: $this")
    }
}

inline fun Any?.logDebug(msg: String? = null) {
    if (msg == null) {
        L.d(this)
    } else {
        L.d("$msg: $this")
    }
}

inline fun Any?.logInfo(msg: String? = null) {
    if (msg == null) {
        L.i(this)
    } else {
        L.i("$msg: $this")
    }
}

inline fun Any?.logWarn(msg: String? = null) {
    if (msg == null) {
        L.w(this)
    } else {
        L.w("$msg: $this")
    }
}

inline fun Any?.logError(msg: String? = null, throwable: Throwable? = null) {
    if (msg == null) {
        L.e("$this", throwable)
    } else {
        L.e("$msg: $this", throwable)
    }
}

inline fun Throwable.wtf(msg: String? = null) {
    if (msg == null) {
        L.wtf(message, this)
    } else {
        L.wtf(msg, this)
    }
}
