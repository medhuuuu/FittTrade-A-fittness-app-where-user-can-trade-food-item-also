package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class doc_bio : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore

    private lateinit var tvJobName : TextView
    private lateinit var etBio : EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc_bio)

        firebaseAuth = FirebaseAuth.getInstance()

        tvJobName = findViewById(R.id.tvjobName)
        etBio = findViewById(R.id.textInputEditText)
        button = findViewById(R.id.saveButton)


        val docUid = FirebaseAuth.getInstance().currentUser!!.uid
        val ref1 = db.collection("doctor").document(docUid)

        button.setOnClickListener {
            val bioText = etBio.text.toString().trim()


            val updateMap = mapOf("bio" to bioText)

            ref1.update(updateMap).addOnSuccessListener {
                /*Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()*/
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

            startActivity(Intent(this, doctor_show::class.java))


        }


    }



}