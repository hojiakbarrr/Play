package com.example.play

import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi

class MainActivity2 : AppCompatActivity() {

    var seekBar: SeekBar? = null
    var start: Button? = null
    var edit: EditText? = null
    var image: ImageView? = null
    var text: TextView? = null

    private var timer: CountDownTimer? = null

    var abc: Long? = null

    var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        seekBar = findViewById(R.id.sekBar)
        edit = findViewById(R.id.editTime)
        start = findViewById(R.id.startTime)
        image = findViewById(R.id.lampochka)

        text = findViewById(R.id.textView2)


        start?.setOnClickListener {
            abc = edit?.text.toString() as Long
//            seekBar?.max = edit?.text.toString().toInt()
            startCountDownTimer(abc!!)
        }


    }

    private fun startCountDownTimer(abc: Long) {
        mp?.stop()
        mp?.reset()
        mp?.release()
        mp = null
        timer?.cancel()

        timer = object : CountDownTimer(abc * 60000, 1000) {
            override fun onTick(timeM: Long) {
                image!!.setImageResource(R.drawable.bulb_off)
                text!!.text = ("" + timeM / 1000)
                val result = (timeM / 1000).toInt()
                seekBar?.apply {
                    progress = result

                }

//
            }

            override fun onFinish() {

                image!!.setImageResource(R.drawable.bulb_on)
                if (mp == null) {
                    mp = MediaPlayer.create(this@MainActivity2, R.raw.alarm_bell)
                    mp!!.start()
                }

            }
        }.start()

    }
}