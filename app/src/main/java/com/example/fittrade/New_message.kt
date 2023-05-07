package com.example.fittrade

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class New_message : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<doc_user>
    private lateinit var myAdapter: MyAdapter
    private lateinit var dbref : DatabaseReference

    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)


        recyclerView= findViewById(R.id.recycle_newmsg)
        recyclerView.layoutManager = LinearLayoutManager(this)
        /*recyclerView.setHasFixedSize(true)*/
        userArrayList = arrayListOf()
        db= FirebaseFirestore.getInstance()
        dbref = FirebaseDatabase.getInstance().getReference("doctor")

        val uid = FirebaseAuth.getInstance().currentUser!!.uid

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (data in snapshot.children){
                        val user = data.getValue(doc_user::class.java)
                        userArrayList.add(user!!)
                    }

                    recyclerView.adapter = MyAdapter(this@New_message, userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@New_message, error.toString(), Toast.LENGTH_SHORT).show()
            }
        })




    }
}
