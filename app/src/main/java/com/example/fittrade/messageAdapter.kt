package com.example.fittrade

import android.content.Context
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth

class messageAdapter(val context: Context, val messagelist : ArrayList<message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val ITEM_REC=1
    val ITEM_SENT=2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType==1){
            val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.chat_from_row,
                parent,false)
            return recieveviewholder(itemView)

        }else{
            val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.chat_to_row,
                parent,false)
            return sentviewholder(itemView)
        }
    }

    override fun getItemCount(): Int {
        return messagelist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentmessage = messagelist[position]
        if(holder.javaClass == sentviewholder::class.java){

            val viewHolder = holder as sentviewholder
            holder.sentmessage.text = currentmessage.message
        }
        else{
            val viewHolder = holder as recieveviewholder
            holder.recievemessage.text = currentmessage.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentmessage= messagelist[position]
        if (FirebaseAuth.getInstance().currentUser?.uid.equals((currentmessage.senderid))){
            return ITEM_SENT
        }else{
            return ITEM_REC
        }
    }
    class sentviewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val sentmessage = itemView.findViewById<TextView>(R.id.textmsgto)
    }
    class recieveviewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val recievemessage = itemView.findViewById<TextView>(R.id.textmsgfrom)
    }
}