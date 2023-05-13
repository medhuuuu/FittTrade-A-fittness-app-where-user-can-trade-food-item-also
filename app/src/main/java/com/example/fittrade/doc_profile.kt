package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView

class doc_profile : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var dbref : DatabaseReference

    private lateinit var imgProfile : CircleImageView
    private lateinit var tvName : TextView
    private lateinit var tvJob : TextView
    private lateinit var tvEmail : TextView
    private lateinit var tvAbout : TextView
    private lateinit var tvPhone : TextView
    private lateinit var editBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc_profile)

        firebaseAuth = FirebaseAuth.getInstance()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dbref = Firebase.database.reference

        imgProfile = findViewById(R.id.img)
        tvName = findViewById(R.id.tvName)
        tvJob = findViewById(R.id.tvJob)
        tvEmail = findViewById(R.id.tvEmail)
        tvAbout = findViewById(R.id.tvAbout)
        tvPhone = findViewById(R.id.tvPhone)
        editBtn = findViewById(R.id.upImg)

        val uid = FirebaseAuth.getInstance().currentUser!!.uid

        dbref.child("doctor").child("doc list").child(uid).get().addOnSuccessListener {
            if (it != null){
                val name = it.child("companyName")?.value.toString()
                val email = it.child("email")?.value.toString()
                val phone = it.child("phone")?.value.toString()
                val about = it.child("bio")?.value.toString()
                val job = it.child("job")?.value.toString()
                val img = it.child("img")?.value.toString()

                tvName.text = name
                tvEmail.text = email
                tvPhone.text = phone
                tvAbout.text = about
                tvJob.text = job

                Glide.with(this).load(img).into(imgProfile)
            }
        }


        editBtn.setOnClickListener {
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@doc_profile, Img_Upload::class.java))
        }



    }
}

