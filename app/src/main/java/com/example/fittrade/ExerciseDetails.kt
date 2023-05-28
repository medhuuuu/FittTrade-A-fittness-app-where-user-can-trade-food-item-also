package com.example.fittrade

import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class ExerciseDetails : AppCompatActivity() {
    private lateinit var videoView : VideoView
    private lateinit var timerBtn : Button
    private lateinit var button: Button
    private lateinit var stepsText : TextView
    private lateinit var countDownTimer: CountDownTimer
    private var Mtimerunning : Boolean = true
    private var Mtimeleftmills : Long?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excercise_details)

        videoView = findViewById(R.id.video_view)
        timerBtn = findViewById(R.id.timer_btn)
        button = findViewById(R.id.start_btn)
        stepsText = findViewById(R.id.steps)


        val url = intent.getStringArrayExtra("videoURL")
        val steps = intent.getStringArrayExtra("steps")


        val stepsString = steps!!.joinToString("\n")
        stepsText.text = stepsString


        button.setOnClickListener {
            if (Mtimerunning){
                startTimer()
            }
            else{
                stopTimer()
            }
        }


        fetchAndPlayVideo(url!!.toList())

    }



    private fun  stopTimer () {
        countDownTimer.cancel()
        Mtimerunning = false
        button.setText("START")
    }

    private fun startTimer() {
        val value1 : CharSequence = timerBtn.text
        val num1 = value1.toString()
        val num2 = num1.substring(0,2)
        val num3 = num1.substring(3,5)

        val number = num2.toInt() * 60 + num3.toInt()
        Mtimeleftmills = number.toLong()*1000

        val timer = object : CountDownTimer(Mtimeleftmills!!, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Mtimeleftmills = millisUntilFinished
                updateTimer()
            }

            override fun onFinish() {
            }

        }
        timer.start()
        button.setText("PAUSE")
        Mtimerunning = true

    }

    private fun updateTimer(){
        val min = Mtimeleftmills?.div(60000)
        min?.toInt()

        val sec = Mtimeleftmills?.rem(60000)?.div(1000)?.toInt()

        var timeLeftText = ""

        if (min!!<10)
            timeLeftText = "0"
        timeLeftText = timeLeftText + min + ":"
        if (sec!!<10)
            timeLeftText+= "0"
        timeLeftText+= sec
        timerBtn.setText(timeLeftText)

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun fetchAndPlayVideo(videoUrl: List<String>) {
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        try {
            val uriList = videoUrl.map { Uri.parse(it) }
            val uri = Uri.parse(videoUrl.first()) // Play the first video in the list
            videoView.setVideoURI(uri)
            videoView.requestFocus()
            videoView.start()
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle the error, such as showing an error message or taking alternative action
        }

//        val uri = Uri.parse(videoUrl.toString())
//        videoView.setVideoURI(uri)
//        videoView.requestFocus()
//        videoView.start()
    }

}