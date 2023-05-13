package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fittrade.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupSeller.setOnClickListener {
            startActivity(Intent(this,select_user::class.java))
        }


        firebaseAuth = FirebaseAuth.getInstance()


        binding.mainsignButton.setOnClickListener {

            val userName = binding.etUser.text.toString()
            val email = binding.etEmail.text.toString()
            val pass = binding.etPass.text.toString()
            val conPass = binding.etConpass.text.toString()
            val phnNo = binding.etPhone.text.toString()
            val place = binding.etPlace.text.toString()
            val age = binding.etAge.text.toString()
            val height =  " "
            val weight = " "
            val bmi = " "

            if(userName.isNotEmpty() && phnNo.isNotEmpty() && age.isNotEmpty() && place.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && conPass.isNotEmpty()){
                if (pass == conPass){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if (it.isSuccessful) {

                            val userId = FirebaseAuth.getInstance().currentUser!!.uid

                            val userMap = hashMapOf(
                                "userName" to userName,
                                "userID" to userId,
                                "email" to email,
                                "pass" to pass,
                                "phn" to phnNo,
                                "place" to place,
                                "age" to age,
                                "height" to height,
                                "weight" to weight,
                                "bmi" to bmi
                            )

                            dbref = FirebaseDatabase.getInstance().getReference("user")
                            dbref.child("user list").child(userId).setValue(userMap).addOnSuccessListener{
                                startActivity(Intent(this, BMI_calculation::class.java))
                                Toast.makeText(this, "Added Done", Toast.LENGTH_SHORT).show()
                            }.addOnFailureListener {
                                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{
                    Toast.makeText(this , "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this , "Empty Fields Are Not Allowed" , Toast.LENGTH_SHORT).show()
            }

        }



    }

}


