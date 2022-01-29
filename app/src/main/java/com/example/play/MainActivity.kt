package com.example.play

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.*

class MainActivity : AppCompatActivity() {


    var mp: MediaPlayer? = null
    var seekBar: SeekBar? = null
    var start: Button? = null
    var stop: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var checkBox = findViewById<CheckBox>(R.id.checkBox1)
        var checkBox1 = findViewById<CheckBox>(R.id.checkBox11)

        var animal = findViewById<TextView>(R.id.textView)

        var radioGroup = findViewById<RadioGroup>(R.id.общий)
        checkBox.isChecked = true



        checkBox.setOnCheckedChangeListener { checkBox, isChecked ->
            if (isChecked) {
                animal.setText("это кот")
                checkBox1.isChecked = false
            } else {
                animal.setText("это собака")
                checkBox1.isChecked = true
            }

        }


        checkBox1.setOnCheckedChangeListener { checkBox1, цц ->
            if (цц) {
                animal.setText("это собака")
                checkBox.isChecked = false
            } else {
                animal.setText("это кот")
                checkBox.isChecked = true
            }
        }

        radioGroup.setOnCheckedChangeListener { radioGroup, id ->

            when (id) {
                R.id.radioButton -> animal.setText("это кот")
                R.id.radioButton2 -> animal.setText("это собака")
            }


        }



        ////////////////***/*/*/*/*//*/*/*/*/*/*//*/*/*/*/*/*/*/*/*/*/*/*/*/*//*/*/*/*/*/*/*//*/*/*/*/*


        seekBar = findViewById(R.id.progressBar2)
        start = findViewById(R.id.startTimer)
        stop = findViewById(R.id.stop)

        start?.setOnClickListener {

            if (mp == null) {
                mp = MediaPlayer.create(this, R.raw.music)
                seekBar?.max = mp!!.duration
                initiliazed()

            }

//            try {
//
//            } catch (e: Exception) {
//                progressBar.progress = 0
//            }

            if (mp != null && mp?.isPlaying == true) {
                mp?.pause()
                start?.text = "Play"
            } else {
                mp?.start()
                start?.text = "Pause"
            }

        }


        stop?.setOnClickListener {
            if (mp != null) {
                mp?.stop()
                mp?.reset()
                mp?.release()
                mp = null
                seekBar?.progress = 0
                start?.text = "play"
            }
        }


        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {


            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, user: Boolean) {
                if (user) {
                    mp?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                mp?.pause()
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                mp?.start()
            }

        })



    }

    private fun initiliazed() {

        seekBar?.max = mp!!.duration
        var handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    seekBar?.progress = mp!!.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {
                    seekBar?.progress = 0
                }
            }
        }, 0)
    }

}