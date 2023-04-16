package com.example.fittrade

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class New_message : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<doc_user>
    private lateinit var myAdapter: MyAdapter

    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)
        recyclerView= findViewById(R.id.recycle_newmsg)
        recyclerView.layoutManager = LinearLayoutManager(this)
        /*recyclerView.setHasFixedSize(true)*/
        userArrayList = arrayListOf()
        db= FirebaseFirestore.getInstance()

        db.collection("doctor").get().addOnSuccessListener {
            if (!it.isEmpty){
                for (data in it.documents){
                    val user : doc_user? = data.toObject<doc_user>(doc_user::class.java)
                    userArrayList.add(user!!)
                }
                recyclerView.adapter = MyAdapter(userArrayList)
            }
        }
            .addOnFailureListener {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }

    }



}
