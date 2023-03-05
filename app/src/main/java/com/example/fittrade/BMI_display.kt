package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.fittrade.databinding.ActivityBmiDisplayBinding

class BMI_display : AppCompatActivity() {
    private lateinit var binding: ActivityBmiDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val first = findViewById<TextView>(R.id.textresult2)
        val intent = intent

        val bmifirst = intent.getStringExtra("firstbmi")
        val bmistr= intent.getIntExtra("value", 0)

        first.text = bmifirst

        if(bmistr>25)
        {

            binding.txtmessage.text = "You are Overweight"
        }
        else if (bmistr>18){
            binding.txtmessage.text = "You are Healthy"
        }
        else if (bmistr<18 && bmistr>5){
            binding.txtmessage.text = "You are Skinny"
        }
        else{
            binding.txtmessage.text= "First Input height and weight"

        }

        binding.nxtbtn.setOnClickListener {
            startActivity(Intent(this, Choose_prog::class.java))
        }




    }
}


