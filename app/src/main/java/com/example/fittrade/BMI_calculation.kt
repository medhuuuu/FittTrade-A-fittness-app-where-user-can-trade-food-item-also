package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.fittrade.databinding.ActivityBmiCalculationBinding
import com.google.firebase.database.DatabaseReference


class BMI_calculation : AppCompatActivity() {
    private lateinit var binding: ActivityBmiCalculationBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBmiCalculationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.button.setOnClickListener {
           calculateBMI()
        }

    }

    private fun calculateBMI() {
        val heightStr = binding.etHeight.text.toString()
        val height = heightStr.toFloat()

        val weightStr = binding.etWeight.text.toString()
        val weight = weightStr.toFloat()

        val bmi = weight / (height * height)
        val bmivalue = bmi.toInt()

        val display = String.format("%.2f", bmi)

        val intent = Intent(this, BMI_display::class.java)
        intent.putExtra("firstbmi", display)
        intent.putExtra("value", bmivalue)
        startActivity(intent)

    }


}
