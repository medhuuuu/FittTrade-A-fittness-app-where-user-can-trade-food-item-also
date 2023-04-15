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
                    val intent= Intent(this, doctor_show::class.java)
                    intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }

            }
        }

        ref2.get().addOnSuccessListener {
            if (it!=null){
                val job = it.data?.get("job")?.toString()

                if (job == "Seller"){
                    val intent= Intent(this, seller_show::class.java)
                    intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }

            }
        }

        ref3.get().addOnSuccessListener {
            if (it!=null){
                val job = it.data?.get("job")?.toString()

                if (job == "Gym-trainer"){
                    val intent= Intent(this, trainer_show::class.java)
                    intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
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