package com.example.fittrade

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.fittrade.databinding.ActivityUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class user_profile : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference

    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvphone: TextView
    private lateinit var tvaddress: TextView
    private lateinit var tvbmi : TextView
    private lateinit var tvweight : TextView
    private lateinit var tvheight: TextView
    private lateinit var tvage: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        tvName = findViewById(R.id.name)
        tvEmail = findViewById(R.id.email)
        tvphone= findViewById(R.id.phoneno)
        tvaddress = findViewById(R.id.address)
        tvbmi = findViewById(R.id.bmivalueshow)
        tvweight = findViewById(R.id.weighteshow)
        tvheight = findViewById(R.id.heighteshow)
        tvage= findViewById(R.id.ageshow)


        val userid = FirebaseAuth.getInstance().currentUser!!.uid

        dbref = Firebase.database.reference

        dbref.child("user").child("user list").child(userid).get().addOnSuccessListener {
            if(it!=null){
                val name = it.child("userName")?.value.toString()
                val email = it.child("email")?.value.toString()
                val phone = it.child("phn")?.value.toString()
                val address = it.child("place").value.toString()
                val age=  it.child("age").value.toString()
                val bmivalue = it.child("bmi")?.value.toString()
                val weight = it.child("weight")?.value.toString()
                val height = it.child("height")?.value.toString()


                tvName.text = name
                tvage.text = age
                tvEmail.text = email
                tvphone.text = phone
                tvaddress.text = address
                tvweight.text = weight
                tvheight.text = height
                tvbmi.text = bmivalue

            }
        }
            .addOnFailureListener {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }

    }
}