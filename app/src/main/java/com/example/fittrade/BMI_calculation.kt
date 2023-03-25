package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.fittrade.databinding.ActivityBmiCalculationBinding


class BMI_calculation : AppCompatActivity() {
    private lateinit var binding: ActivityBmiCalculationBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBmiCalculationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            calculateBMI()
        }

    }

    private fun calculateBMI() {
        val heightStr = binding.etHeight.text.toString()
        val height = heightStr.toFloat()

        val weightStr = binding.etWeight.text.toString()
        val weight = weightStr.toFloat()

        val bmi = weight / (height * height)
        val bmivalue = bmi.toInt()

        val display = String.format("%.2f", bmi)

        val intent = Intent(this, BMI_display::class.java)
        intent.putExtra("firstbmi", display)
        intent.putExtra("value", bmivalue)
        startActivity(intent)

        val bmiMap = hashMapOf(
            "height" to heightStr,
            "weight" to weight,
            "bmi" to bmivalue
        )

        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        db.collection("bmi").document(userId).set(bmiMap).addOnSuccessListener {
            Toast.makeText(this, "Added Done", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }

    }


}
