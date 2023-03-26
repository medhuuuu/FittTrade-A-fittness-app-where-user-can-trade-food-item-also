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

        setData()

        val docUid = FirebaseAuth.getInstance().currentUser!!.uid

        val ref = db.collection("doctor").document(docUid)

        ref.get().addOnSuccessListener {
            if (it != null) {
                val job = it.data?.get("job")?.toString()
                tvJobName.text = job
            }
        }

        db.collection("gym-trainer").document(docUid).get().addOnSuccessListener {
            if (it != null) {
                val job = it.data?.get("job")?.toString()
                tvJobName.text = job
            }
        }



        button.setOnClickListener {
            val bioText = etBio.text.toString().trim()


            val updateMap = mapOf("bio" to bioText)

            ref.update(updateMap).addOnSuccessListener {
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

            startActivity(Intent(this, doc_profile::class.java))


        }


    }

    private fun setData(){
        val docUid = FirebaseAuth.getInstance().currentUser!!.uid

        db.collection("doctor").document(docUid).get().addOnSuccessListener {

            if (it != null) {
                val bio =it.data?.get("bio")?.toString()

                etBio.setText(bio)
            }
            Toast.makeText(this, "Added Done", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}