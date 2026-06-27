package com.mendhak.sheepyhorn

import android.media.MediaPlayer
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current

            val player = remember {
                MediaPlayer.create(context, R.raw.sheepyhorn)
            }

            var pressed by remember { mutableStateOf(false) }

            DisposableEffect(player) {
                onDispose {
                    player.release()
                }
            }

            Image(
                painter = painterResource(
                    if (pressed) {
                        R.drawable.awesome2
                    } else {
                        R.drawable.awesome1
                    }
                ),
                contentDescription = "Press",
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .pointerInteropFilter { event ->
                        when (event.action) {
                            MotionEvent.ACTION_DOWN -> {
                                pressed = true
                                if (!player.isPlaying) {
                                    player.start()
                                }
                                true
                            }

                            MotionEvent.ACTION_UP,
                            MotionEvent.ACTION_CANCEL -> {
                                pressed = false
                                true
                            }

                            else -> false
                        }
                    },
                contentScale = ContentScale.Fit
            )
        }
    }
}
