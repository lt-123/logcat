import org.junit.Test
import xyz.liut.logcat.L
import xyz.liut.logcat.LogLevel
import xyz.liut.logcat.Logcat
import xyz.liut.logcat.handler.StdHandler
import xyz.liut.logcat4kt.*

class JUnitTest {

    @Test
    fun logTest() {
        // 添加 LogHandler
        Logcat.getHandlers().add(StdHandler())

        println("----------------")

        L.v("verbose 1")
        L.d("debug 1")
        L.i("info 1")
        L.w("warn 1")
        L.e("warn 1")

        println("--------设置 level 为 debug--------")
        Logcat.setLevel(LogLevel.DEBUG)

        "verbose".logVerbose("--tag--")
        "debug ".logDebug("--tag--")
        "info ".logInfo("--tag--")
        "warn ".logWarn("--tag--")
        "error ".logError("--tag--")

        println("--------设置 level 为 warn--------")
        Logcat.setLevel(LogLevel.WARN)

        "verbose".logVerbose()
        "debug ".logDebug()
        "info ".logInfo()
        "warn ".logWarn()
        "error ".logError()

        println("-------设置 level 为 info---------")
        Logcat.setLevel(LogLevel.INFO)

        "verbose".logVerbose()
        "debug ".logDebug()
        "info ".logInfo()
        "warn ".logWarn()
        "error ".logError()


    }

}