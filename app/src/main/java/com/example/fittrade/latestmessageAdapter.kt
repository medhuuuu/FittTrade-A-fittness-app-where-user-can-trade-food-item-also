package com.example.fittrade

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView
import latestmsgclass

class latestmessageAdapter(private val context: Context, private val userlist: ArrayList<latestmsgclass>) : RecyclerView.Adapter<latestmessageAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.showlist,
            parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.latestmessage.text= userlist[position].message
        holder.senderid.text= userlist[position].senderid


    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    class MyViewHolder(ItemView : View): RecyclerView.ViewHolder(ItemView){
        val latestmessage : TextView =ItemView.findViewById(R.id.tvrecycle_phone)
        val senderid : TextView= ItemView.findViewById(R.id.tvrecycle_username)



    }
}

