package com.decagon.week3assignment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "ALERT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create Notification Channel
        createNotificationChannel()


        val btnNotif = findViewById<Button>(R.id.btnNotif)
        btnNotif.setOnClickListener {
            addNotification()
        }


        val btnSecondActivity = findViewById<Button>(R.id.btnSecondActivity)
        btnSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("INACTIVE", "INACTIVE")
            }
            startActivity(intent)
        }
    }

    private fun addNotification() {

        val intent = Intent(this, SecondActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra("ACTIVE", "ACTIVE")
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)


        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.insta_time)
                .setContentTitle("Notification")
                .setContentText("New Message")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set intent that will execute when tapped
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {

            // notificationId should be unique for each notification
            notify(0, builder.build())
        }
    }

    //Create NotificationChannel
    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = getString(R.string.channel_name)
            val channelDescription = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, channelName, importance).apply {
                description = channelDescription
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
