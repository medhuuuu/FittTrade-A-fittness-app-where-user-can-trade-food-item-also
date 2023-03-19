package com.example.fittrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class sellersignup : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore

    private lateinit var etCname : EditText
    private lateinit var etEmail : EditText
    private lateinit var etPhone : EditText
    private lateinit var etPlace : EditText
    private lateinit var etPass : EditText
    private lateinit var etConpass : EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sellersignup)

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

            if(companyName.isNotEmpty() && phone.isNotEmpty() && place.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && conPass.isNotEmpty()){
                if (pass == conPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val sellerMap = hashMapOf(
                                "companyName" to companyName,
                                "email" to email,
                                "phone" to phone,
                                "pass" to pass,
                                "conPass" to conPass
                            )

                            val sellerId = FirebaseAuth.getInstance().currentUser!!.uid

                            db.collection("seller").document(sellerId).set(sellerMap).addOnSuccessListener {
                                Toast.makeText(this, "Added Done", Toast.LENGTH_SHORT).show()
                            }.addOnFailureListener {
                                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
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