package com.example.fittrade

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class MyAdapter(private val context: Context, private val userlist: ArrayList<doc_user>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.showlist,
            parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.companyName.text= userlist[position].companyName
        holder.phone.text = userlist[position].phone

        Glide.with(context).load(userlist[position].img).into(holder.image)




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

