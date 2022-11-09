package com.example.serviceandroid

import android.R
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.serviceandroid.MainActivity.Companion.CHANNEL_ID


/**
 * Created by Giancarlos Quilca on 8/10/2022.
 */
class MiService: Service(){
    companion object {
        const val ONGOING_NOTIFICATION_ID = 1
        const val DEFAULT_CHANNEL_ID = "miscellaneous"
        const val COMMAND = "COMMAND"
        const val COMMAND_START = "COMMAND_START"
        const val COMMAND_STOP = "COMMAND_STOP"
    }
    private val TAG: String = "MiServicio"
    private val handler: Handler = Handler()
    private var i = 0
    init {
        Log.d(TAG, "Services running...")
    }
    override fun onBind(p0: Intent?): IBinder? = null


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        handler.apply {
            val runnable = object :Runnable {
                override fun run() {
                    Log.d(TAG, "Nueva Notificacion ${i++}")
                    //showNotification()
                    postDelayed(this, 5000)
                }
            }
            postDelayed(runnable, 1000)
        }
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Example Service")
            .setContentText("fs")
            .setSmallIcon(R.drawable.ic_btn_speak_now)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)
        return Service.START_NOT_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        Log.d(TAG, "Service Destroyed...")

    }

    private  fun showNotification() {

        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0, intent, 0
        )


        val notification = NotificationCompat.Builder(
            applicationContext,
            MainActivity.CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_btn_speak_now)
            .setContentTitle("New Task")
            .setContentText("Subscribe on the channel")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channelName = "Channel Name"
            val channelDescription = "Channel Description"
            val channelImportance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(MainActivity.CHANNEL_ID, channelName, channelImportance).apply {
                description = channelDescription
            }

            val notificationManager = applicationContext.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }


        with(NotificationManagerCompat.from(applicationContext)) {
            notify(MainActivity.NOTIFICATION_ID, notification.build())
        }

    }
}