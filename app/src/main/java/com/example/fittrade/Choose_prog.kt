package com.example.fittrade

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.fittrade.databinding.ActivityChooseProgBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Choose_prog : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    lateinit var binding: ActivityChooseProgBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChooseProgBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.exercise.setOnClickListener {
            val userId = FirebaseAuth.getInstance().currentUser!!.uid
            dbref = FirebaseDatabase.getInstance().getReference("user").child("user list").child(userId)
            dbref.get().addOnSuccessListener {
                val gender = it.child("gender")?.value.toString()
                val bmi = it.child("bmi")?.value.toString()

                if (gender == "Male"){
                    val intent = Intent(this, MaleExercise::class.java)
                    intent.putExtra("gender", gender)
                    intent.putExtra("bmi", bmi)
                    startActivity(intent)
                }

                if (gender == "Female"){
                    val intent = Intent(this, planexercise::class.java)
                    intent.putExtra("gender", gender)
                    intent.putExtra("bmi", bmi)
                    startActivity(intent)
                }

            }
        }

        binding.food.setOnClickListener {
            startActivity(Intent(this, AllFoodItemsSearch::class.java))
        }
        binding.doctor.setOnClickListener {
            startActivity(Intent(this, docandtrainer::class.java))
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

                        startActivity(Intent(this@Choose_prog, caloriebudget::class.java))
                    }
                    R.id.orderhistory -> {
                        startActivity(Intent(this@Choose_prog, orderhistory::class.java))
                    }

                    R.id.logoutnavi -> {
                        startActivity(Intent(this@Choose_prog, MainActivity::class.java))

                    }
                    R.id.purchase -> {
                        startActivity(Intent(this@Choose_prog, user_prod_show::class.java))

                    }

                    R.id.cal -> {
                        startActivity(Intent(this@Choose_prog, caloriecalculation::class.java))
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