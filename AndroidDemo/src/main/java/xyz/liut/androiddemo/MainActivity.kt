package xyz.liut.androiddemo

import android.os.Bundle
import android.os.Process
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import xyz.liut.logcat.L
import xyz.liut.logcat.LogLevel
import xyz.liut.logcat.android.AndroidLogcatHandler
import xyz.liut.logcat.handler.RollbackFileHandler
import xyz.liut.logcat.handler.StdHandler
import xyz.liut.logcat.kt.logDebug
import xyz.liut.test.R
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        L
            .getDefault()
            // 等级
            .setLevel(LogLevel.VERBOSE)
            //输出到控制台
            .addHandler(AndroidLogcatHandler("LOGCAT_" + Process.myPid() + "_"))
            // 标准输出 System.out
            .addHandler(StdHandler(false, true, false))
            // 输出到文件
            .addHandler(
                RollbackFileHandler(
                    getExternalFilesDir("logcat").toString(),
                    1,
                    "yyyy-MM-dd-HH-mm-ss'.log'"
                )
            )
            // 自定义输出，到屏幕
            .addHandler { _, tag, msg ->
                runOnUiThread {
                    with(tv_txt) {
                        append("\n")
                        append(tag)
                        append(": ")
                        append(msg)
                    }
                }
            }

        bt.setOnClickListener {
            logDebug("value = ${Date()}")
        }

    }

}
