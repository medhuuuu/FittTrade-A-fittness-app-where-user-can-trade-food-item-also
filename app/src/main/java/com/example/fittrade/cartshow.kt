package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittrade.databinding.ActivityCartshowBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class cartshow : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<productClass>
    private lateinit var dbref : DatabaseReference
    private lateinit var db : FirebaseAuth
    private lateinit var binding: ActivityCartshowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartshowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView= findViewById(R.id.cartshow)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userArrayList = arrayListOf()
        db= FirebaseAuth.getInstance()
        dbref= FirebaseDatabase.getInstance().getReference()
        val user= db.currentUser?.uid


        binding.cartview.setOnClickListener  {
            Toast.makeText(this@cartshow, "Thanks for buying", Toast.LENGTH_SHORT).show()
            //dbref.child("Purchase list").child(user!!).setValue(null)
            startActivity(Intent(this, Choose_prog::class.java))

        }



        dbref.child("Purchase list").child(user!!).addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val user = snapshot.getValue(productClass::class.java)
                userArrayList.add(user!!)
                recyclerView.adapter= sellerlistadapter(this@cartshow, userArrayList)

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }
}