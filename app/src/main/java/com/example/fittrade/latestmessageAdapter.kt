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
import java.sql.Ref

class latestmessageAdapter(val context: Context,val userlist: ArrayList<latestmsgclass>) : RecyclerView.Adapter<latestmessageAdapter.MyViewHolder>() {


    var chatpartneruser: doc_user?=null
        var currentchatpartneruser: UserList?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.showlist, parent,
            false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.latestmessage.text= userlist[position].message


        var chatpartnerId: String?= null

        var chatpartnername: String? = null

        if(userlist[position].senderid== FirebaseAuth.getInstance().uid){
            chatpartnerId = userlist[position].receiverid.toString()
        }else{
            chatpartnerId = userlist[position].senderid.toString()
        }


        fun onBindViewHolderUsernamelist() {
            FirebaseDatabase.getInstance().getReference("/user/user list/$chatpartnerId").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    currentchatpartneruser = snapshot.getValue(UserList::class.java)
                    chatpartnername = currentchatpartneruser?.userName?.toString()
                    holder.senderid.text = chatpartnername
                }

                override fun onCancelled(error: DatabaseError) {
                    throw NotImplementedError("An operation is not implemented: Not yet implemented")
                }
            })
        }

        FirebaseDatabase.getInstance().getReference("/doctor/doc list/$chatpartnerId")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    chatpartneruser = snapshot.getValue(doc_user::class.java)
                    chatpartnername= chatpartneruser?.companyName.toString()
                    if (chatpartnername == "null") {
                        onBindViewHolderUsernamelist()
                    } else {
                        holder.senderid.text = chatpartnername
                    }
                }


                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })



        /*FirebaseDatabase.getInstance().getReference("/user/user list/$chatpartnerId")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    currentchatpartneruser = snapshot.getValue(UserList::class.java)

                    holder.senderid.text= currentchatpartneruser?.userName

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })*/



        holder.itemView.setOnClickListener{
            val intent = Intent(context, latestchatlog::class.java)
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

