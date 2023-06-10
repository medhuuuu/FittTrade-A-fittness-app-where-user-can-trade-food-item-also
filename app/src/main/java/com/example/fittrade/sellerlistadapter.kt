package com.example.fittrade

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class sellerlistadapter(val context: Context, val productlist: ArrayList<productClass> ) : RecyclerView.Adapter<sellerlistadapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sellerlistadapter.MyViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.productshow,
            parent,false)
        return sellerlistadapter.MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentuser = productlist[position]

        holder.itemName.text= productlist[position].itemName
        holder.price.text = productlist[position].price
        holder.companyName.text= productlist[position].companyName
    }

    override fun getItemCount(): Int {
        return productlist.size
    }

    class MyViewHolder(ItemView : View): RecyclerView.ViewHolder(ItemView){

        val itemName : TextView =ItemView.findViewById(R.id.tvrecycle_product_name)
        val price : TextView = ItemView.findViewById(R.id.tvrecycle_price)
        val companyName : TextView = ItemView.findViewById(R.id.tvrecycle_companyname)


    }

}