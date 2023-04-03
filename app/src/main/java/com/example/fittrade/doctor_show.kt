package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.fittrade.databinding.ActivityDoctorShowBinding
import com.example.fittrade.databinding.ActivitySellerShowBinding

class doctor_show : AppCompatActivity() {
    lateinit var binding: ActivityDoctorShowBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorShowBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@doctor_show,
                drawyerLayout,
                R.string.open,
                R.string.close
            )
            drawyerLayout.addDrawerListener(toggle)
            toggle.syncState()


            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView3.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.provider_profile -> {
                        startActivity(Intent(this@doctor_show, doc_profile::class.java))
                        /*Toast.makeText(this@Choose_prog, "User Profile", Toast.LENGTH_SHORT).show()*/

                    }

                    R.id.logout_provider -> {
                        startActivity(Intent(this@doctor_show, MainActivity::class.java))

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