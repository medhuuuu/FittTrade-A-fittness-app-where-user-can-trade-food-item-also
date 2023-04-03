package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.fittrade.databinding.ActivitySellersignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Objects

class sellersignup : AppCompatActivity() {
    private lateinit var binding: ActivitySellersignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore

    private lateinit var etCname : EditText
    private lateinit var etEmail : EditText
    private lateinit var etPhone : EditText
    private lateinit var etPlace : EditText
    private lateinit var etPass : EditText
    private lateinit var etConpass : EditText
    private lateinit var etSpinner: Spinner
    private lateinit var etJob : TextView
    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellersignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etSpinner = findViewById(R.id.spinbar)
        etJob = findViewById(R.id.spinshow)

        val profession_names = arrayOf("Doctor","Seller","Gym-trainer")
        val arrayAdapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,profession_names)
        etSpinner.adapter = arrayAdapter

        etSpinner.onItemSelectedListener = object :

        AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                parent?.getItemAtPosition(position).toString()
                etJob.text= profession_names[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }


        firebaseAuth = FirebaseAuth.getInstance()

        etCname = findViewById(R.id.et_selleruser)
        etEmail = findViewById(R.id.et_email)
        etPhone = findViewById(R.id.et_sellerphone)
        etPlace = findViewById(R.id.et_sellerplace)
        etPass = findViewById(R.id.et_sellerpass)
        etConpass = findViewById(R.id.et_sellerconpass)
        button = findViewById(R.id.sellersignButton)


        button.setOnClickListener {
            val companyName = etCname.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val place = etPlace.text.toString().trim()
            val pass = etPass.text.toString().trim()
            val conPass = etConpass.text.toString().trim()
            val jobText = etJob.text.toString().trim()
            val bio = " "

            if(companyName.isNotEmpty() && phone.isNotEmpty() && place.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && conPass.isNotEmpty()){
                if (pass == conPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val sellerMap = hashMapOf(
                                "companyName" to companyName,
                                "email" to email,
                                "phone" to phone,
                                "pass" to pass,
                                "job" to jobText,
                                "bio" to bio
                            )

                            val sellerId = FirebaseAuth.getInstance().currentUser!!.uid

                            if (jobText == "Doctor"){
                                db.collection("doctor").document(sellerId).set(sellerMap).addOnSuccessListener {
                                    /*Toast.makeText(this, "Added Done", Toast.LENGTH_SHORT).show()*/
                                    startActivity(Intent(this, doc_bio::class.java))
                                }.addOnFailureListener {
                                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                                }
                            }
                            else if (jobText == "Seller"){
                                db.collection("seller").document(sellerId).set(sellerMap).addOnSuccessListener {
                                    Toast.makeText(this, "Added Done", Toast.LENGTH_SHORT).show()
                                }.addOnFailureListener {
                                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                                }
                            }
                            else if (jobText == "Gym-trainer"){
                                db.collection("gym-trainer").document(sellerId).set(sellerMap).addOnSuccessListener {
                                    /*Toast.makeText(this, "Added Done", Toast.LENGTH_SHORT).show()*/
                                    startActivity(Intent(this, Trainer_Bio::class.java))
                                }.addOnFailureListener {
                                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                        else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{
                    Toast.makeText(this , "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this , "Empty Fields Are Not Allowed" , Toast.LENGTH_SHORT).show()
            }
        }

    }
}