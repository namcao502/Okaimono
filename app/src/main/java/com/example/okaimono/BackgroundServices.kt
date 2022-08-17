package com.example.okaimono

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast


class BackgroundServices : Service() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.okaimono)
        mediaPlayer!!.setVolume(100f, 100f)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (mediaPlayer!!.isPlaying){
            mediaPlayer!!.pause()
            mediaPlayer!!.seekTo(0)
        }
        mediaPlayer!!.start()
        Toast.makeText(applicationContext, "Okaimono", Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    override fun onDestroy() {
        mediaPlayer!!.stop()
        mediaPlayer!!.release()
    }

}