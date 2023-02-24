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
            val height = binding.height.toString()
            val dobheight= height.toDouble()
            val weight= binding.weight.toString()
            val dobweight = weight.toDouble()
            val bmi= dobweight/(dobheight * dobheight)

            binding.res.text = String.format("your bmi is : %.2f", bmi)
        }

    }


}
