package com.example.play

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*

class MainActivity2 : AppCompatActivity() {

    var seekBar: SeekBar? = null
    var start: Button? = null
    var edit: EditText? = null
    var image: ImageView? = null
    var text: TextView? = null
    var time: TextView? = null

    private var timer: CountDownTimer? = null

    var abc: Long? = null

    var starPoint = 0

    var endPoint = 0

    var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        seekBar = findViewById(R.id.sekBar)
        edit = findViewById(R.id.editTime)
        start = findViewById(R.id.startTime)
        image = findViewById(R.id.lampochka)

        text = findViewById(R.id.textView2)
        time = findViewById(R.id.minutes)

        abc = 4
        start?.setOnClickListener {
//            abc = edit?.text.toString().toLong()
//            seekBar?.max = edit?.text.toString().toInt()
            startCountDownTimer(abc!!)
        }


        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                time?.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                if (seekBar != null) {
//                    starPoint = seekBar.progress
//
//                }
//                startCountDownTimer(starPoint!!.toLong())
                //                startCountDownTimer(text.toString().toLong()!!)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    endPoint = seekBar.progress

                }
                startCountDownTimer(endPoint!!.toLong())

//                seekBar?.apply {
//                    progress = endPoint
//                }

                //                startCountDownTimer(text.toString().toLong()!!)
            }

        })
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
                time!!.text = ("" + timeM / 60000)
                text!!.text = (":" + timeM / 1000)  ////*/*/*/*/*/секунды можно ускорить нолик добавив и на экране тоже + цифра
                val result = (timeM / 60000).toInt()
//                seekBar?.apply {
//                    progress = result
//
//                }
                seekBar!!.progress = result
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