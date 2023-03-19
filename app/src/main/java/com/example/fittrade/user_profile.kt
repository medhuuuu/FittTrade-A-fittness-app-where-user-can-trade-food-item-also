package com.example.fittrade

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.fittrade.databinding.ActivityUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class user_profile : AppCompatActivity() {
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvphone: TextView
    private lateinit var tvaddress: TextView

    private var db = Firebase.firestore
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        tvName = findViewById(R.id.name)
        tvEmail = findViewById(R.id.email)
        tvphone= findViewById(R.id.phoneno)
        tvaddress = findViewById(R.id.address)


        val userid = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = db.collection("user").document(userid)
        ref.get().addOnSuccessListener {
            if(it!=null){
                val name = it.data?.get("userName")?.toString()
                val email = it.data?.get("email")?.toString()
                val phone = it.data?.get("phn")?.toString()
                val address = it.data?.get("place").toString()

                tvName.text = name
                tvEmail.text = email
                tvphone.text = phone
                tvaddress.text = address

            }
        }
            .addOnFailureListener {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }

    }
}