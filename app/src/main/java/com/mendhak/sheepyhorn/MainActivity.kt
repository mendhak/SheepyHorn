package com.mendhak.sheepyhorn

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class MainActivity : AppCompatActivity(), View.OnClickListener, MediaPlayer.OnCompletionListener {

    private lateinit var player: MediaPlayer
    private var alreadyPlaying: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player = MediaPlayer.create(applicationContext, R.raw.sheepyhorn)
        player.setOnCompletionListener(this)

        findViewById<ImageButton>(R.id.sound_button).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(!alreadyPlaying){
            player.start();
            alreadyPlaying = true
        }
    }

    override fun onCompletion(mp: MediaPlayer?) {
        alreadyPlaying = false
    }

}