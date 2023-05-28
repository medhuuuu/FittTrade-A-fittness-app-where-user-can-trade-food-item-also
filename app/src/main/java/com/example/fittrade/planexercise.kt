package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fittrade.databinding.ActivityPlanexerciseBinding

class planexercise : AppCompatActivity() {
    lateinit var binding: ActivityPlanexerciseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanexerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gender = intent.getStringExtra("gender")
        val bmi = intent.getStringExtra("bmi")

        binding.beginnerPlanBtn.setOnClickListener {
            val intent = Intent(this, ExercisesActivity::class.java)
            intent.putExtra("type", "Beginner")
            intent.putExtra("gender", gender)
            intent.putExtra("bmi", bmi)
            startActivity(intent)
        }

        binding.interPlanBtn.setOnClickListener {
            val intent = Intent(this, ExercisesActivity::class.java)
            intent.putExtra("type", "Intermediate")
            intent.putExtra("gender", gender)
            intent.putExtra("bmi", bmi)
            startActivity(intent)
        }


        binding.advancePlanBtn.setOnClickListener {
            val intent = Intent(this, ExercisesActivity::class.java)
            intent.putExtra("type", "Advanced")
            intent.putExtra("gender", gender)
            intent.putExtra("bmi", bmi)
            startActivity(intent)
        }

        binding.arm.setOnClickListener {
            val intent = Intent(this, ExercisesActivity::class.java)
            intent.putExtra("type", "Biceps")
            intent.putExtra("gender", gender)
            intent.putExtra("bmi", bmi)
            startActivity(intent)
        }

        binding.belly.setOnClickListener {
            val intent = Intent(this, ExercisesActivity::class.java)
            intent.putExtra("type", "Abdominals")
            intent.putExtra("gender", gender)
            intent.putExtra("bmi", bmi)
            startActivity(intent)
        }

        binding.butt.setOnClickListener {
            val intent = Intent(this, ExercisesActivity::class.java)
            intent.putExtra("type", "Glutes")
            intent.putExtra("gender", gender)
            intent.putExtra("bmi", bmi)
            startActivity(intent)
        }

        binding.chest.setOnClickListener {
            val intent = Intent(this, ExercisesActivity::class.java)
            intent.putExtra("type", "Chest")
            intent.putExtra("gender", gender)
            intent.putExtra("bmi", bmi)
            startActivity(intent)
        }

        binding.thigh.setOnClickListener {
            val intent = Intent(this, ExercisesActivity::class.java)
            intent.putExtra("type", "Quads")
            intent.putExtra("gender", gender)
            intent.putExtra("bmi", bmi)
            startActivity(intent)
        }

        binding.yoga.setOnClickListener {
            val intent = Intent(this, ExercisesActivity::class.java)
            intent.putExtra("type", "Yoga")
            intent.putExtra("gender", gender)
            intent.putExtra("bmi", bmi)
            startActivity(intent)
        }

    }


}