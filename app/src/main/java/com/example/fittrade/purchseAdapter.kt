package com.example.fittrade

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class purchseAdapter(val context: Context, val productarray: ArrayList<productClass>): RecyclerView.Adapter<purchseAdapter.MyViewHolder>() {
    private lateinit var dbref : DatabaseReference
    private lateinit var db : FirebaseAuth

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): purchseAdapter.MyViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.productshow,
            parent,false)
        return purchseAdapter.MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: purchseAdapter.MyViewHolder, position: Int) {
        val currentuser = productarray[position]
        db = FirebaseAuth.getInstance()
        val user = db.currentUser?.uid
        holder.itemName.text= productarray[position].itemName
        holder.price.text = productarray[position].price
        holder.companyName.text= productarray[position].companyName

        val itemmap = hashMapOf(
            "itemName" to holder.itemName.text,
            "price" to holder.price.text,
            "companyNmae" to  holder.companyName.text
        )

        dbref= FirebaseDatabase.getInstance().getReference()
        holder.itemView.setOnClickListener{
            dbref.child("Purchase list").child(user!!).push().setValue(itemmap)
        }
    }

    override fun getItemCount(): Int {
        return productarray.size
    }

    class MyViewHolder(ItemView : View): RecyclerView.ViewHolder(ItemView){

        val itemName : TextView =ItemView.findViewById(R.id.tvrecycle_product_name)
        val price : TextView = ItemView.findViewById(R.id.tvrecycle_price)
        val companyName : TextView = ItemView.findViewById(R.id.tvrecycle_companyname)


    }



}
