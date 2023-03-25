package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.fittrade.databinding.ActivityChooseProgBinding
import com.google.android.material.navigation.NavigationView

class Choose_prog : AppCompatActivity() {
    lateinit var binding: ActivityChooseProgBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChooseProgBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.exercise.setOnClickListener {
            startActivity(Intent(this, exercise_provide::class.java))
        }

        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@Choose_prog,
                drawyerLayout,
                R.string.open,
                R.string.close
            )
            drawyerLayout.addDrawerListener(toggle)
            toggle.syncState()


            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.firstItem -> {
                        startActivity(Intent(this@Choose_prog, user_profile::class.java))
                        /*Toast.makeText(this@Choose_prog, "User Profile", Toast.LENGTH_SHORT).show()*/

                    }
                    R.id.secondid -> {

                        Toast.makeText(this@Choose_prog, "Analyzing", Toast.LENGTH_SHORT).show()
                    }
                    R.id.thirditem -> {
                        Toast.makeText(this@Choose_prog, "History", Toast.LENGTH_SHORT).show()
                    }

                    R.id.logoutnavi -> {
                        startActivity(Intent(this@Choose_prog, MainActivity::class.java))

                    }
                }
                true

            }



        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}