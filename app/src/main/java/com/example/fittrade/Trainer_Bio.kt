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

class Trainer_Bio : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore

    private lateinit var tvJobName2 : TextView
    private lateinit var etBio2 : EditText
    private lateinit var button2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainer_bio)
        firebaseAuth = FirebaseAuth.getInstance()

        etBio2 = findViewById(R.id.trainerbio)
        button2 = findViewById(R.id.saveButton2)
        val docUid = FirebaseAuth.getInstance().currentUser!!.uid
        val ref2 = db.collection("gym-trainer").document(docUid)

        button2.setOnClickListener {
            val bioText = etBio2.text.toString().trim()


            val updateMap = mapOf("bio" to bioText)
            ref2.update(updateMap).addOnSuccessListener {
                /*Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()*/
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

            startActivity(Intent(this, trainer_show::class.java))


        }
    }
}