package com.example.fittrade

import android.content.Context
import android.content.Intent
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
        val description = list[position].data.description
        val portion = list[position].data.portion
        val energy = list[position].data.foodNutrients.Energy.value
        val fat = list[position]?.data?.foodNutrients?.Fat?.fattyAcidsTotalSaturated?.value ?: 0.05
        val protein = list[position].data.foodNutrients.Protein.value ?: 0.00
        val carbs = list[position].data.foodNutrients.Carbonhydrate.value ?: 0.00

        holder.foodName.text = description
        holder.portion.text = portion.toString() + "g"
        holder.energy.text = energy.toString() +
                list[position].data.foodNutrients.Energy.unitname.toString()


        holder.itemView.setOnClickListener {
            val intent = Intent(context, FoodDetails::class.java)
            intent.putExtra("name", description)
            intent.putExtra("portion", portion.toString())
            intent.putExtra("energy", energy.toString())
            intent.putExtra("fat", fat.toString())
            intent.putExtra("protein", protein.toString())
            intent.putExtra("carbs", carbs.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}