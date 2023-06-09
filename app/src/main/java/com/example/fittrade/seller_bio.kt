package com.example.fittrade

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class seller_bio : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore
    private lateinit var dbref : DatabaseReference

    private lateinit var etBio3 : EditText
    private lateinit var button3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_bio)
        firebaseAuth = FirebaseAuth.getInstance()

        etBio3 = findViewById(R.id.etbio3)
        button3 = findViewById(R.id.button3)

        val docUid = FirebaseAuth.getInstance().currentUser!!.uid

        dbref = Firebase.database.reference

        button3.setOnClickListener {
            val bioText = etBio3.text.toString().trim()


            val updateMap = mapOf("bio" to bioText)

            dbref.child("seller").child("seller list").child(docUid).updateChildren(updateMap).addOnSuccessListener {
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, seller_show::class.java))
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        }
    }
}