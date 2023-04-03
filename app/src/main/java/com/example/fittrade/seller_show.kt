package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.fittrade.databinding.ActivitySellerShowBinding

class seller_show : AppCompatActivity() {
    lateinit var binding: ActivitySellerShowBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySellerShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@seller_show,
                drawyerLayout,
                R.string.open,
                R.string.close
            )
            drawyerLayout.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navview2.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.provider_profile -> {
                       startActivity(Intent(this@seller_show, seller_profile::class.java))
                    }
                    R.id.logout_provider -> {
                        startActivity(Intent(this@seller_show, MainActivity::class.java))
                    }
                }
                true
            }
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}