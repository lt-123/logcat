package xyz.liut.logcat.kt

import org.junit.Test
import xyz.liut.logcat.L
import xyz.liut.logcat.LogLevel
import xyz.liut.logcat.Logcat
import xyz.liut.logcat.handler.StdHandler

class Logcat4KtKtTest {

    @Test
    fun logTest() {
        // 添加 LogHandler
        val logcat: Logcat = L.getDefault().addHandler(StdHandler())

        println("----------------")
        test()

        println("--------设置 level 为 debug--------")
        logcat.level = LogLevel.DEBUG
        test()

        println("--------设置 level 为 warn--------")
        logcat.level = LogLevel.WARN
        test()

        println("-------设置 level 为 info---------")
        logcat.level = LogLevel.INFO
        test()

        println("-------关闭 log---------")
        logcat.level = LogLevel.NO
        test()


    }

    private fun test() {
        logVerbose("verbose")
        logDebug("debug")
        logInfo("info")
        logWarn("warn")
        logError("error")
        logWtf("what the fuck")

        println("----")

        logVerbose("verbose", tag = "tag")
        logDebug("debug", tag = "tag")
        logInfo("info", tag = "tag")
        logWarn("warn", tag = "tag")
        logError("error", tag = "tag")
        logWtf("what the fuck", tag = "tag")

        println("----")

        NullPointerException("npe").printLogcat()
    }

}
