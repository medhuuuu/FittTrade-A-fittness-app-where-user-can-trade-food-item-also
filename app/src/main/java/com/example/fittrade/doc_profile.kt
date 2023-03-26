package com.example.fittrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class doc_profile : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore

    private lateinit var tvName : TextView
    private lateinit var tvJob : TextView
    private lateinit var tvEmail : TextView
    private lateinit var tvAbout : TextView
    private lateinit var tvPhone : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc_profile)

        firebaseAuth = FirebaseAuth.getInstance()

        tvName = findViewById(R.id.tvName)
        tvJob = findViewById(R.id.tvJob)
        tvEmail = findViewById(R.id.tvEmail)
        tvAbout = findViewById(R.id.tvAbout)
        tvPhone = findViewById(R.id.tvPhone)

        val uid = FirebaseAuth.getInstance().currentUser!!.uid

        db.collection("doctor").document(uid).get().addOnSuccessListener {
            if (it != null){
                val name = it.data?.get("companyName")?.toString()
                val email = it.data?.get("email")?.toString()
                val phone = it.data?.get("phone")?.toString()
                val about = it.data?.get("bio")?.toString()
                val job = it.data?.get("job")?.toString()

                tvName.text = name
                tvEmail.text = email
                tvPhone.text = phone
                tvAbout.text = about
                tvJob.text = job
            }
        }

        db.collection("gym-trainer").document(uid).get().addOnSuccessListener {
            if (it != null){
                val name = it.data?.get("companyName")?.toString()
                val email = it.data?.get("email")?.toString()
                val phone = it.data?.get("phone")?.toString()
                val about = it.data?.get("bio")?.toString()
                val job = it.data?.get("job")?.toString()

                tvName.text = name
                tvEmail.text = email
                tvPhone.text = phone
                tvAbout.text = about
                tvJob.text = job
            }
        }
    }
}