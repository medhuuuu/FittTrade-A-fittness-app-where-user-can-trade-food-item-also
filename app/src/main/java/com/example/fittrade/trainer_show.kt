package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.fittrade.databinding.ActivityTrainerShowBinding

class trainer_show : AppCompatActivity() {
    lateinit var binding: ActivityTrainerShowBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTrainerShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@trainer_show,
                drawyerLayout,
                R.string.open,
                R.string.close
            )

            drawyerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView4.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.provider_profile->{
                        startActivity(Intent(this@trainer_show, trainer_profile::class.java))
                    }
                    R.id.logout_provider->{
                        startActivity(Intent(this@trainer_show,MainActivity::class.java))
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