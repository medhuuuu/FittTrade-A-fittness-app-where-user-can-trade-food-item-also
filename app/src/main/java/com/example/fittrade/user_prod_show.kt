package com.example.fittrade

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittrade.databinding.ActivityUserProdShowBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class user_prod_show : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<productClass>
    private lateinit var dbref : DatabaseReference
    private lateinit var db : FirebaseAuth
    private lateinit var binding: ActivityUserProdShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserProdShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView= findViewById(R.id.userprodshow)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userArrayList = arrayListOf()
        db= FirebaseAuth.getInstance()
        val user= db.currentUser?.uid
        dbref = FirebaseDatabase.getInstance().getReference("Seller_product_list")



        binding.cartview.setOnClickListener {
                startActivity(Intent(this, cartshow::class.java))
            }

        dbref.child("seller").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val sellerlist= snapshot.getValue(productClass::class.java)
                userArrayList.add(sellerlist!!)
                recyclerView.adapter= purchseAdapter(this@user_prod_show, userArrayList)
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