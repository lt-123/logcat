package xyz.liut.androiddemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import xyz.liut.logcat.L
import xyz.liut.logcat.Logcat
import xyz.liut.logcat.handler.StdHandler
import xyz.liut.logcat4android.AndroidLogcatHandler
import xyz.liut.logcat4kt.logDebug
import xyz.liut.test.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Logcat.getHandlers().add(AndroidLogcatHandler("DEMO"))
        Logcat.getHandlers().add(StdHandler())

    }


    fun onClick(v: View) {
        L.e("aaaaaaaa")

        "bbbbb".logDebug()

    }

}
