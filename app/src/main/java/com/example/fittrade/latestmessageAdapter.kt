package com.example.fittrade

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import latestmsgclass

class latestmessageAdapter(private val context: Context, private val userlist: ArrayList<latestmsgclass>) : RecyclerView.Adapter<latestmessageAdapter.MyViewHolder>() {
        var chatpartneruser: doc_user?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.showlist, parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.latestmessage.text= userlist[position].message
        val chatpartnerId: String
        if(userlist[position].senderid== FirebaseAuth.getInstance().uid){
            chatpartnerId = userlist[position].receiverid.toString()
        }else{
            chatpartnerId = userlist[position].senderid.toString()
        }

        FirebaseDatabase.getInstance().getReference("/doctor/doc list/$chatpartnerId")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    chatpartneruser = snapshot.getValue(doc_user::class.java)

                    holder.senderid.text= chatpartneruser?.companyName

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })


        //holder.senderid.text= userlist[position].receiverid

        holder.itemView.setOnClickListener{
            val intent = Intent(context, chatlog_Activity::class.java)
            intent.putExtra("senderid", userlist[position].senderid)
            intent.putExtra("receiverid", userlist[position].receiverid)
            context.startActivity(intent)
        }




    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    class MyViewHolder(ItemView : View): RecyclerView.ViewHolder(ItemView){
        val latestmessage : TextView =ItemView.findViewById(R.id.tvrecycle_phone)
        val senderid : TextView= ItemView.findViewById(R.id.tvrecycle_username)




    }
}

