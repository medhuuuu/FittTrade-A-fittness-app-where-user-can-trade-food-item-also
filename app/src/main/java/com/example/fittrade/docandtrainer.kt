package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fittrade.databinding.ActivityDocandtrainerBinding

class docandtrainer : AppCompatActivity() {
    lateinit var binding: ActivityDocandtrainerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDocandtrainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.doclistchoose.setOnClickListener {
            startActivity(Intent(this, New_message::class.java))

        }
        binding.trainerlistchoose.setOnClickListener {
            startActivity(Intent(this, Trainer_log::class.java))
        }



    }
}