package xyz.liut.logcat4kt

import xyz.liut.logcat.L

fun Any?.logVerbose(tag: String? = null) {
    L.v(tag, this)
}

fun Any?.logDebug(tag: String? = null) {
    L.d(tag, this)
}

fun Any?.logInfo(tag: String? = null) {
    L.i(tag, this)
}

fun Any?.logWarn(tag: String? = null) {
    L.w(tag, this)
}

fun Any?.logError(tag: String? = null, throwable: Throwable? = null) {
    L.e(tag, this, throwable)
}


//fun main() {
//
//    Logcat.getHandlers().add(StdHandler())
//
//    "verbose".logVerbose("----")
//    "debug ".logDebug("----")
//    "info ".logInfo("----")
//    "warn ".logWarn("----")
//    "error ".logError("----")
//
//
//    "v 2".logVerbose("----")
//    "d 2 ".logDebug("----")
//    "i2 ".logInfo("----")
//    "w2 ".logWarn("----")
//    "e2 ha ".logError("----")
//
//}