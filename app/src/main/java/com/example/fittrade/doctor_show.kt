package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.fittrade.databinding.ActivityDoctorShowBinding
import com.example.fittrade.databinding.ActivitySellerShowBinding
import com.google.firebase.auth.FirebaseAuth

class doctor_show : AppCompatActivity() {
    lateinit var binding: ActivityDoctorShowBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorShowBinding.inflate(layoutInflater)
        setContentView(binding.root)


        veryfyisuserloggedin()
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
                        FirebaseAuth.getInstance().signOut()
                        val intent = Intent(this@doctor_show, MainActivity::class.java)
                        intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                    }
                    R.id.new_msg -> {
                        startActivity(Intent(this@doctor_show, New_message::class.java))
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