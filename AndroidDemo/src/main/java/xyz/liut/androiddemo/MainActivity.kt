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
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 文件名
        val fileNamePattern = "yyyy-MM-dd-HH-mm-ss'.log'"

        L
            .getDefault()
            .setLevel(LogLevel.VERBOSE) // 等级
            .addHandler(AndroidLogcatHandler("LOGCAT_" + Process.myPid() + "_"))    //输出到控制台
            .addHandler(StdHandler())   // 标准输出 System.out
            .addHandler(
                RollbackFileHandler(
                    getExternalFilesDir("logcat").toString(),
                    1,
                    fileNamePattern
                )
            )   // 输出问文件
            .addHandler { _, tag, msg ->
                runOnUiThread {
                    with(tv_txt) {
                        append("\n")
                        append(tag)
                        append(": ")
                        append(msg)
                    }
                }
            }   // 自定义输出

        bt.setOnClickListener {
            "value = ${Random.nextInt()}".logDebug()
        }

    }

}
