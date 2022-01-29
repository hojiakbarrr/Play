package com.example.play

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {

    private lateinit var mp: MediaPlayer
    private var totalTime: Int = 0

    private lateinit var click: Button

    var seekBar: SeekBar? = null
    var volumeBar: SeekBar? = null

    var elopsedTimelabel: TextView? = null
    var remainingTimelabel: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        click = findViewById(R.id.playBtnClick)

        mp = MediaPlayer.create(this, R.raw.soldat)
        mp.isLooping = true
        mp.setVolume(0.5f, 0.5f)
        totalTime = mp.duration

        seekBar = findViewById(R.id.sekkbar)
        volumeBar = findViewById(R.id.volumeBar)

        elopsedTimelabel = findViewById(R.id.elopsedTimelabel)
        remainingTimelabel = findViewById(R.id.remainingTimelabel)

        volumeBar!!.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean,
                ) {

                    if (fromUser) {
                        var volumeNum = progress / 100.0f
                        mp.setVolume(volumeNum, volumeNum)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }

            }
        )

        seekBar!!.max = totalTime
        seekBar!!.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekbar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean,
                ) {
                    if (fromUser) {
                        mp.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }

            }
        )
        Thread(Runnable {
            while (mp != null) {
                try {
                    var msg = Message()
                    msg.what = mp.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {

                }
            }
        }).start()

    }

    @SuppressLint("Handleleak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {

            var currentPosition = msg.what

            ////update sekbar
            seekBar!!.progress = currentPosition

            ///Update labels
            var elapsedTime = createTimeLabel(currentPosition)
            elopsedTimelabel!!.text = elapsedTime

            var remainingTime = createTimeLabel(totalTime - currentPosition)
            remainingTimelabel!!.text = "-$remainingTime"
        }

    }

    private fun createTimeLabel(time: Int): String {

        var timeLabel = ""
        var min = time / 1000 / 60
        var sec = time / 1000 / 60

        timeLabel = "$min:"
        if(sec < 10) timeLabel += "0"
        timeLabel += sec

        return timeLabel
    }


    fun playBtnClick(v: View) {
        if (mp.isPlaying) {
            ////*/*Stop
            mp.pause()
            click.setBackgroundResource(R.drawable.play)
        } else {
            ///*///*start
            mp.start()
            click.setBackgroundResource(R.drawable.pause)
        }
    }

}