package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fittrade.databinding.ActivityLoginBinding
import com.example.fittrade.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginbtn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val pass = binding.etPass.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(this, Choose_prog::class.java))
                    }
                    else{
                        Toast.makeText(this , it.exception.toString() , Toast.LENGTH_SHORT).show()
                    }
                }

            }
            else{
                Toast.makeText(this , "Empty Fields Are Not Allowed" , Toast.LENGTH_SHORT).show()
            }
        }
    }


//    override fun onStart() {
//        super.onStart()
//        if(firebaseAuth.currentUser != null){
//            startActivity(Intent(this, Choose_prog::class.java))
//        }
//    }
}