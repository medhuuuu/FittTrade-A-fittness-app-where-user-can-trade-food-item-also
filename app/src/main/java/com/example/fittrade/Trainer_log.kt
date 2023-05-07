package com.example.fittrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Trainer_log : AppCompatActivity() {
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

        dbref = FirebaseDatabase.getInstance().getReference("gym-trainer")

        val uid = FirebaseAuth.getInstance().currentUser!!.uid

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (data in snapshot.children){
                        val user = data.getValue(doc_user::class.java)
                        userArrayList.add(user!!)
                    }

                    recyclerView.adapter = MyAdapter(this@Trainer_log, userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Trainer_log, error.toString(), Toast.LENGTH_SHORT).show()
            }
        })

    }

}