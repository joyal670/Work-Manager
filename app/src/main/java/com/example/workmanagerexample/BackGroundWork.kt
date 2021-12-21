package com.example.workmanagerexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class BackGroundWork(private var context: Context, params: WorkerParameters) :
    Worker(context, params) {
    override fun doWork(): Result {
        val msg = inputData.getString("msg")

        return try {
            if (msg.isNullOrEmpty()) {
                Log.e("TAG", "doWork: Msg null or empty")
                Result.failure()
            }

            createNotification("Success", msg.toString())
            Log.e("TAG", "doWork: Success" )

            Result.success()
        } catch (throwable: Throwable) {
            Log.e("TAG", "doWork: Msg false$throwable")
            Result.failure()
        }
    }

    private fun createNotification(title: String, description: String) {

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel("101", "channel", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder = NotificationCompat.Builder(applicationContext, "101")
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setSmallIcon(R.drawable.ic_launcher_background)

        notificationManager.notify(1, notificationBuilder.build())

    }
}