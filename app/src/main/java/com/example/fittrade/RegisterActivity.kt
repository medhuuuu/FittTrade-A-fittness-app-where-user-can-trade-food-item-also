package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fittrade.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore

    private lateinit var etUser: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var etPhone: EditText
    private lateinit var etPlace: EditText
    private lateinit var etConpass: EditText
    private lateinit var etAge: EditText
    private  lateinit var mainsignButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupSeller.setOnClickListener {
            startActivity(Intent(this,sellersignup::class.java))
        }


        firebaseAuth = FirebaseAuth.getInstance()

        etUser = findViewById(R.id.et_user)
        etEmail = findViewById(R.id.et_email)
        etPass = findViewById(R.id.et_pass)
        etPhone = findViewById(R.id.et_phone)
        etPlace = findViewById(R.id.et_place)
        etConpass = findViewById(R.id.et_conpass)
        etAge = findViewById(R.id.et_age)
        mainsignButton = findViewById(R.id.mainsignButton)

        mainsignButton.setOnClickListener {

            val userName = etUser.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val pass = etPass.text.toString().trim()
            val conPass = etConpass.text.toString().trim()
            val phnNo = etPhone.text.toString().trim()
            val place = etPlace.text.toString().trim()
            val age = etAge.text.toString().trim()

            if(userName.isNotEmpty() && phnNo.isNotEmpty() && age.isNotEmpty() && place.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && conPass.isNotEmpty()){
                if (pass == conPass){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if (it.isSuccessful){
                            val userMap = hashMapOf(
                                "userName" to userName,
                                "email" to email,
                                "pass" to pass,
                                "phn" to phnNo,
                                "place" to place,
                                "age" to age
                            )

                            val userId = FirebaseAuth.getInstance().currentUser!!.uid

                            db.collection("user").document(userId).set(userMap).addOnSuccessListener {
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


