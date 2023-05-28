package com.example.fittrade

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fittrade.dataModel.ExerciseDataResponse

class ExerciseAdapter(val context: Context, val list: ArrayList<ExerciseDataResponse.ExerciseDataResponseItem>) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.excercise_card, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.excerciseName.text = list[position].exercise_name
        holder.excerciseTime.text = list[position].Force

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ExerciseDetails::class.java)
            intent.putExtra("videoURL", list[position].videoURL.toTypedArray())
            intent.putExtra("steps", list[position].steps.toTypedArray())
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val excerciseName : TextView = itemView.findViewById(R.id.tvrecycle_excercise_name)
        val excerciseTime : TextView = itemView.findViewById(R.id.tvrecycle_time)
    }

}