package com.zrq.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    private val myBinder = MyBinder()

    override fun onBind(intent: Intent?): IBinder {
        return myBinder
    }


    class MyBinder : MyAidl.Stub() {

        override fun request(json: String?) {
            Log.d(TAG, "request: $json")
        }
    }

    companion object {
        const val TAG = "MyService"
    }
}