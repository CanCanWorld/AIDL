package com.zrq.ui

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import com.zrq.service.MyAidl

class MainActivity : AppCompatActivity() {

    private var binder: MyAidl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connect = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.d(TAG, "onServiceConnected: ")
                binder = MyAidl.Stub.asInterface(service)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
            }
        }

        Intent().apply {
            setPackage("com.zrq.ui")
            type = "${SystemClock.elapsedRealtime()}"
            component = ComponentName("com.zrq.service", "com.zrq.service.MyService")
            bindService(this, connect, BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.btn).setOnClickListener {
            binder?.request("hhh")
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}