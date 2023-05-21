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

class MyAdapter(val context: Context, val userlist: ArrayList<doc_user>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.showlist,
            parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentuser = userlist[position]

        holder.companyName.text= userlist[position].companyName
        holder.phone.text = userlist[position].phone

        Glide.with(context).load(userlist[position].img).into(holder.image)
        holder.itemView.setOnClickListener{
            val intent = Intent(context, chatlog_Activity::class.java)
            intent.putExtra("companyName", currentuser.companyName )
            intent.putExtra("id", currentuser.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    class MyViewHolder(ItemView : View): RecyclerView.ViewHolder(ItemView){
        val image : CircleImageView = ItemView.findViewById(R.id.tvrecycle_img)
        val companyName : TextView =ItemView.findViewById(R.id.tvrecycle_username)
        val phone : TextView = ItemView.findViewById(R.id.tvrecycle_phone)


    }
}

