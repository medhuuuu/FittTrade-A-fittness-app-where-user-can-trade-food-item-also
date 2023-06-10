package com.example.fittrade

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FoodDetails : AppCompatActivity() {
    private lateinit var foodName : TextView
    private lateinit var portion : TextView
    private lateinit var energy : TextView
    private lateinit var protein : TextView
    private lateinit var fat : TextView
    private lateinit var carbs : TextView
    private lateinit var addFood : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_details)

        val name = intent.getStringExtra("name")
        val portionString = intent.getStringExtra("portion")
        val energyString = intent.getStringExtra("energy")
        val proteinString = intent.getStringExtra("protein")
        val fatString = intent.getStringExtra("fat")
        val carbsString = intent.getStringExtra("carbs")

        foodName = findViewById(R.id.food_name)
        portion = findViewById(R.id.portion)
        energy = findViewById(R.id.energy)
        protein = findViewById(R.id.protein)
        fat = findViewById(R.id.fat)
        carbs = findViewById(R.id.carbs)
        addFood = findViewById(R.id.add_food)

        foodName.text = name
        portion.text = portionString
        energy.text = energyString
        protein.text = proteinString
        fat.text = fatString
        carbs.text = carbsString

        addFood.setOnClickListener {
            val intent = Intent(this, caloriebudget::class.java)
            intent.putExtra("energy", energyString)
            intent.putExtra("fat", fatString)
            intent.putExtra("protein", proteinString)
            intent.putExtra("carbs", carbsString)
            startActivity(intent)
        }


    }
}