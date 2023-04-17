package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.fittrade.databinding.ActivityTrainerShowBinding
import com.google.firebase.auth.FirebaseAuth

class trainer_show : AppCompatActivity() {
    lateinit var binding: ActivityTrainerShowBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTrainerShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        veryfyisuserloggedin()


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
                       FirebaseAuth.getInstance().signOut()
                        val intent= Intent(this@trainer_show,MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }

                    R.id.new_msg ->{
                        startActivity(Intent(this@trainer_show, New_message::class.java))
                    }
                    R.id.trainer_msg ->{
                        startActivity(Intent(this@trainer_show, Trainer_log::class.java))
                    }

                    R.id.seller_msg ->{
                        startActivity(Intent(this@trainer_show, Seller_log::class.java))
                    }
                }
                true
            }
            }

    }

    private fun veryfyisuserloggedin() {
        val uid= FirebaseAuth.getInstance().uid
        if(uid==null){
            val intent= Intent(this, MainActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}