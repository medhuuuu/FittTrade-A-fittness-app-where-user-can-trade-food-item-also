package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import  com.example.fittrade.LoginActivity
import com.example.fittrade.RegisterActivity
import com.example.fittrade.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginbtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.signupbtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}