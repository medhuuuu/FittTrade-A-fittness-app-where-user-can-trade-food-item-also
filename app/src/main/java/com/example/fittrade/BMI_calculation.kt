package com.example.fittrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.fittrade.databinding.ActivityBmiCalculationBinding


class BMI_calculation : AppCompatActivity() {
    private lateinit var binding: ActivityBmiCalculationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBmiCalculationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.button.setOnClickListener {
            calculateBMI()
        }

    }
    private fun calculateBMI(){
        val height = binding.height.toString()
        val h= height.toFloat()

        val weight= binding.weight.toString()
        val w = weight.toFloat()
        val bmi= w/(h * h)
        val dispaly = bmi.toString()
        binding.res.text = dispaly
    }

}
