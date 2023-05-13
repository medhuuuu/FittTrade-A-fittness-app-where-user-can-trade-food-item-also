package com.example.fittrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import okhttp3.internal.cache.DiskLruCache.Snapshot

class chatlog_Activity : AppCompatActivity() {
    private lateinit var messageRecycleview : RecyclerView
    private lateinit var messagebox : EditText
    private lateinit var sendbtn : ImageView
    private lateinit var messageAdapter: messageAdapter
    private lateinit var messaglist : ArrayList<message>
    private lateinit var dbref : DatabaseReference
    private var db = Firebase.firestore

    var receiverRoom : String?=null
    var senderRoom : String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatlog)


        val name = intent.getStringExtra("companyName")
        val recieveruid = intent.getStringExtra("id")
        val senderuid = FirebaseAuth.getInstance().currentUser?.uid
        senderRoom = recieveruid + senderuid
        receiverRoom = senderuid + recieveruid
        supportActionBar?.title = name

        db.collection("ChatChannels")
        dbref = FirebaseDatabase.getInstance().getReference("doctor")
        messageRecycleview = findViewById(R.id.chatRecycler)
        messagebox = findViewById(R.id.messageBox)
        sendbtn = findViewById(R.id.sendmsgimg)
        messaglist= ArrayList()
        messageAdapter = messageAdapter(this, messaglist)
        messageRecycleview.layoutManager = LinearLayoutManager(this)
        messageRecycleview.adapter = messageAdapter

        dbref.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    messaglist.clear()
                    for(postSnapshot in snapshot.children){
                        val message =postSnapshot.getValue(message::class.java)
                        messaglist.add(message!!)
                    }

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })



        sendbtn.setOnClickListener{
            val message = messagebox.text.toString()
            val messageobject = message(message,senderuid)
            dbref.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageobject).addOnSuccessListener {
                    dbref.child("chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageobject)
                }
            messagebox.setText("")
        }
    }


}