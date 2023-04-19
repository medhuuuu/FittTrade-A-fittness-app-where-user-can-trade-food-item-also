package com.example.fittrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class chatlog_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatlog)
        supportActionBar?.title="chatlog"
    }
}