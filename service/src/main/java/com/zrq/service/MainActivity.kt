package com.zrq.service

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var binder: MyService.MyBinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val connection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.d("TAG", "onServiceConnected: ")
                binder = service as MyService.MyBinder
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d("TAG", "onServiceDisconnected: ")
            }
        }


        Thread {
            Log.d(TAG, "bindService: ")
            while (bindService(Intent(this, MyService::class.java), connection, BIND_AUTO_CREATE)) {
                Thread.sleep(200)
            }
        }.start()

        findViewById<Button>(R.id.btn).setOnClickListener {
            binder?.request("www")
        }

    }

    companion object {
        const val TAG = "MainActivity"
    }
}