package com.example.fittrade

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class Img_Upload : AppCompatActivity() {
    private var storage = Firebase.storage
    private lateinit var dbref : DatabaseReference
    private lateinit var uri: Uri

    private lateinit var imageUp : ImageView
    private lateinit var browseBtn : Button
    private lateinit var uploadBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img_upload)

        storage = FirebaseStorage.getInstance()

        dbref = Firebase.database.reference

        imageUp = findViewById(R.id.upImg)
        browseBtn = findViewById(R.id.Browser)
        uploadBtn = findViewById(R.id.Upload)

        val gallaryImg = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                imageUp.setImageURI(it)
                uri = it!!
            }
        )

        val uid = FirebaseAuth.getInstance().currentUser!!.uid


        browseBtn.setOnClickListener {
            gallaryImg.launch("image/*")
        }

        uploadBtn.setOnClickListener {
            storage.getReference("images").child(System.currentTimeMillis().toString())
                .putFile(uri)
                .addOnSuccessListener { task->
                    task.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener { uri->

                            val imgMap = mapOf("img" to uri.toString())

                            dbref.child("doctor").child(uid).updateChildren(imgMap)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this, doc_profile::class.java))
                            }.addOnFailureListener {
                                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                                }

                        }
                }
        }


    }
}