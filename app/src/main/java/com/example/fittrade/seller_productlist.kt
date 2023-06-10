package com.example.fittrade

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class seller_productlist : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<productClass>
    private lateinit var dbref : DatabaseReference
    private lateinit var db : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_productlist)

        recyclerView= findViewById(R.id.sellerproductlist)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userArrayList = arrayListOf()
        db= FirebaseAuth.getInstance()
        dbref = FirebaseDatabase.getInstance().getReference("Seller_item_list")

        val currentuser = db.currentUser?.uid

        dbref.child(currentuser!!).addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val sellerlist= snapshot.getValue(productClass::class.java)
                userArrayList.add(sellerlist!!)
                recyclerView.adapter= sellerlistadapter(this@seller_productlist, userArrayList)
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

        /*dbref.child(currentuser!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (data in snapshot.children){
                        val user = data.getValue(productClass::class.java)
                        userArrayList.add(user!!)
                    }

                    recyclerView.adapter= sellerlistadapter(this@seller_productlist, userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@seller_productlist, error.toString(), Toast.LENGTH_SHORT).show()
            }
        })*/


    }
}