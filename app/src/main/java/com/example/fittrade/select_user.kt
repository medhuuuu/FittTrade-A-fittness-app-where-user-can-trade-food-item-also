package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fittrade.databinding.ActivitySelectUserBinding

class select_user : AppCompatActivity() {
    private lateinit var binding: ActivitySelectUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sellerimg.setOnClickListener {
            startActivity(Intent(this,sellersignup::class.java))
        }
        binding.docimg.setOnClickListener {
            startActivity(Intent(this,sellersignup::class.java))
        }
        binding.fitimg.setOnClickListener {
            startActivity(Intent(this,sellersignup::class.java))
        }

    }
}