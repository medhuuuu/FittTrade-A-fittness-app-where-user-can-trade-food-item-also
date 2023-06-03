package com.example.fittrade

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class caloriebudget : AppCompatActivity() {
    private lateinit var calorieIntake : TextView
    private lateinit var proteinTextView: TextView
    private lateinit var carbTextView: TextView
    private lateinit var fatTextView: TextView
    private lateinit var calorieLeftTextView: TextView
    private lateinit var button: Button

    private lateinit var dbref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caloriebudget)

        val portion = intent.getStringExtra("portion")
        val energy = intent.getStringExtra("energy")
        val energyNum = energy?.toDouble()

        calorieIntake = findViewById(R.id.calorie_intake)
        proteinTextView = findViewById(R.id.protein)
        carbTextView = findViewById(R.id.carbs)
        fatTextView = findViewById(R.id.fat)
        calorieLeftTextView = findViewById(R.id.calorie_left)
        button = findViewById(R.id.add_btn)


        val userid = FirebaseAuth.getInstance().currentUser!!.uid
        dbref = Firebase.database.reference

        dbref.child("user").child("user list").child(userid).get().addOnSuccessListener {
            if (it.exists()){
                val calorie = it.child("calorie As plan")?.value.toString()
                val weight = it.child("weight")?.value.toString()

                val protein = weight.toInt() * 0.8
                val fat = (calorie.toDouble() * 0.30)/9.0
                val carbs = (calorie.toDouble() - protein -fat)/4.0

                val calorieDisplay = String.format("%.2f", calorie.toDouble())
                val fatDisplay = String.format("%.2f", fat)
                val carbsDisplay = String.format("%.2f", carbs)


                calorieIntake.text = calorieDisplay + "Kcal"
                proteinTextView.text = protein.toString() +"g"
                carbTextView.text = carbsDisplay + "g"
                fatTextView.text = fatDisplay + "g"

                button.setOnClickListener {
                    val intent = Intent(this, AllFoodItemsSearch::class.java)
                    startActivity(intent)

                }

//                val leftCal = calorie.toDouble() - energyNum!!
//                calorieLeftTextView.text = leftCal.toString() + "Kcal"
            }
        }
    }
}