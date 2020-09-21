package xyz.liut.androiddemo

import android.os.Bundle
import android.os.Process
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import xyz.liut.logcat.L
import xyz.liut.logcat.LogLevel
import xyz.liut.logcat.android.AndroidLogcatHandler
import xyz.liut.logcat.handler.StdHandler
import xyz.liut.logcat.kt.logDebug
import xyz.liut.test.R
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        L
            .getDefault()
            .setLevel(LogLevel.VERBOSE)
            .addHandler(AndroidLogcatHandler("LOGCAT_" + Process.myPid() + "_"))
            .addHandler(StdHandler())
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

        val random = Random(100)
        bt.setOnClickListener {
            "value = ${random.nextInt()}".logDebug()
        }

    }

}
