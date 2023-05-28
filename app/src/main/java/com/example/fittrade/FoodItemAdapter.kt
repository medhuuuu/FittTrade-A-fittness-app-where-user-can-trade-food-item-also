package com.example.fittrade

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fittrade.dataModel.FoodItemInfoData

class FoodItemAdapter(val context: Context, var list: ArrayList<FoodItemInfoData>) : RecyclerView.Adapter<FoodItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodName : TextView = itemView.findViewById(R.id.recycle_food_name)
        val portion : TextView = itemView.findViewById(R.id.recycle_portion)
        val energy : TextView = itemView.findViewById(R.id.recycle_energy)
    }

    fun setFilteredList(list: ArrayList<FoodItemInfoData>){
        this.list = list
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_cal_item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.foodName.text = list[position].data.description
        holder.portion.text = list[position].data.portion.toString() + "g"
        holder.energy.text = list[position].data.foodNutrients.Energy.value.toString() + list[position].data.foodNutrients.Energy.unitname.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}