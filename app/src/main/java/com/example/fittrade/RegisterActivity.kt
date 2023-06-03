package com.example.fittrade

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fittrade.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbref : DatabaseReference
    private lateinit var etSpinner: Spinner
    private lateinit var etJob : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etSpinner = findViewById(R.id.spinbarnew)
        etJob = findViewById(R.id.spinshownew)

        val gender_names = arrayOf("Male", "Female", "Others")
        val arrayAdapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,gender_names)
        etSpinner.adapter = arrayAdapter

        etSpinner.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                parent?.getItemAtPosition(position).toString()
                etJob.text= gender_names[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

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
            val genderText = etJob.text.toString()

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
                                "height" to "",
                                "weight" to "",
                                "bmi" to "",
                                "gender" to genderText,
                                "calorie As plan" to "",
                                "bmr" to ""
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


