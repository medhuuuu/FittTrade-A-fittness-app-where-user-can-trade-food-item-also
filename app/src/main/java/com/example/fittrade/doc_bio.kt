package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class doc_bio : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore
    private lateinit var dbref : DatabaseReference

    private lateinit var etBio : EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc_bio)

        firebaseAuth = FirebaseAuth.getInstance()

        etBio = findViewById(R.id.textInputEditText)
        button = findViewById(R.id.saveButton)

        dbref = Firebase.database.reference



        button.setOnClickListener {
            val bioText = etBio.text.toString().trim()
            val docUid = FirebaseAuth.getInstance().currentUser!!.uid


            val updateMap = mapOf("bio" to bioText)

            dbref.child("doctor").child("doc list").child(docUid).updateChildren(updateMap).addOnSuccessListener {
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, doctor_show::class.java))
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }



        }


    }



}