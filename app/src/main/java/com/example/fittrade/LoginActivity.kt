package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fittrade.databinding.ActivityLoginBinding
import com.example.fittrade.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore
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

        binding.loginbtn2.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val pass = binding.etPass.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        val uid = FirebaseAuth.getInstance().currentUser!!.uid
                        checkUserAccess(uid)
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

    private fun checkUserAccess(uid: String) {

        val ref1 = db.collection("doctor").document(uid)
        val ref2 = db.collection("seller").document(uid)
        val ref3 = db.collection("gym-trainer").document(uid)

        ref1.get().addOnSuccessListener {
            if (it!=null){
                val job = it.data?.get("job")?.toString()

                if (job == "Doctor"){
                    startActivity(Intent(this, doctor_show::class.java))
                }

            }
        }

        ref2.get().addOnSuccessListener {
            if (it!=null){
                val job = it.data?.get("job")?.toString()

                if (job == "Seller"){
                    startActivity(Intent(this, seller_show::class.java))
                }

            }
        }

        ref3.get().addOnSuccessListener {
            if (it!=null){
                val job = it.data?.get("job")?.toString()

                if (job == "Gym-trainer"){
                    startActivity(Intent(this, trainer_show::class.java))
                }

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