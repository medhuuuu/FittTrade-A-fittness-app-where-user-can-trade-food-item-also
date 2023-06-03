package com.example.fittrade

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class caloriecalculation : AppCompatActivity() {
    private lateinit var heightView : TextView
    private lateinit var weightView : TextView
    private lateinit var ageView : TextView
    private lateinit var genderView : TextView
    private lateinit var actvity : Spinner
    private lateinit var activityText : TextView
    private lateinit var goal : Spinner
    private lateinit var goalTextView: TextView
    private lateinit var calcuBtn: Button

    private lateinit var dbref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caloriecalculation)

        heightView = findViewById(R.id.height)
        weightView = findViewById(R.id.weight)
        ageView = findViewById(R.id.age)
        genderView = findViewById(R.id.gender)
        actvity = findViewById(R.id.activitylevel)
        activityText = findViewById(R.id.activityleveltext)
        goal = findViewById(R.id.weightplan)
        goalTextView = findViewById(R.id.weightplanshowtext)
        calcuBtn = findViewById(R.id.calculateBtn)

        val userid = FirebaseAuth.getInstance().currentUser!!.uid
        dbref = Firebase.database.reference


        val activityLevelNames = arrayOf(
            "1.Sedentary: Little walking or no exercise",
            "2.Lightly Active: Exercise 1-3 times/week",
            "3.Moderately Active: Exercise 4-5 times/week",
            "4.Very Active: Daily exercise or hard exercise",
            "5.Extra Active: Intense exercise 6-7 times/week")

        val activityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, activityLevelNames)
        actvity.adapter = activityAdapter

        actvity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                parent?.getItemAtPosition(position).toString()
                activityText.text = activityLevelNames[position].substring(0,1)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }


        val goals = arrayOf("maintain", "weightlose", "weightgain")

        val goalAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, goals)
        goal.adapter = goalAdapter

        goal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                parent?.getItemAtPosition(position).toString()
                goalTextView.text = goals[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }



        dbref.child("user").child("user list").child(userid).get().addOnSuccessListener {
            if (it != null) {
                val weight = it.child("weight")?.value.toString()
                val height = it.child("height")?.value.toString()
                val age=  it.child("age")?.value.toString()
                val gender = it.child("gender")?.value.toString()


                heightView.text = height
                weightView.text = weight
                ageView.text = age
                genderView.text = gender

                val ageNum = age.toInt()
                val heightNum = height.toDouble()*100
                val weightNum = weight.toDouble()


                calcuBtn.setOnClickListener {
                    val activityLevel = activityText.text.toString()
                    val activityLevelInt = activityLevel.toInt()

                    val goalLevel = goalTextView.text.toString()

                    getData(ageNum, gender, heightNum, weightNum, activityLevelInt, goalLevel)

                }

            }

        }

    }

    private fun getData(ageNum: Int, gender: String, heightNum: Double, weightNum: Double, activityLevel: Int, goalLevel: String) {

        if (gender.lowercase().equals("male")){
            val bmr = 10*weightNum + 6.25*heightNum - 5*ageNum +5
            getActivityLevel(activityLevel, bmr, goalLevel)
        }

        else if (gender.lowercase().equals("female")){
            val bmr = 10*weightNum + 6.25*heightNum - 5*ageNum -161
            getActivityLevel(activityLevel, bmr, goalLevel)
        }
    }

    private fun getActivityLevel(activityLevel: Int, bmr: Double, goalLevel: String) {
        val userid = FirebaseAuth.getInstance().currentUser!!.uid

        if (activityLevel == 1){
            val TDEE = bmr*1.2
            val calorie = getCalorie(goalLevel, TDEE)
            val userMap = hashMapOf(
                "calorie As plan" to calorie,
                "bmr" to bmr
            )
            dbref.child("user").child("user list").child(userid)
                .updateChildren(userMap as Map<String, Any>).addOnSuccessListener {
                    val intent = Intent(this, caloriebudget::class.java)
                    startActivity(intent)
                }
        }
        else if (activityLevel == 2){
            val TDEE = bmr*1.375
            val calorie = getCalorie(goalLevel, TDEE)
            val userMap = hashMapOf(
                "calorie As plan" to calorie,
                "bmr" to bmr
            )
            dbref.child("user").child("user list").child(userid)
                .updateChildren(userMap as Map<String, Any>).addOnSuccessListener {
                    val intent = Intent(this, caloriebudget::class.java)
                    startActivity(intent)
                }
        }
        else if (activityLevel == 3){
            val TDEE = bmr*1.55
            val calorie = getCalorie(goalLevel, TDEE)
            val userMap = hashMapOf(
                "calorie As plan" to calorie,
                "bmr" to bmr
            )
            dbref.child("user").child("user list").child(userid)
                .updateChildren(userMap as Map<String, Any>).addOnSuccessListener {
                    val intent = Intent(this, caloriebudget::class.java)
                    startActivity(intent)
                }
        }
        else if (activityLevel == 4){
            val TDEE = bmr*1.725
            val calorie = getCalorie(goalLevel, TDEE)
            val userMap = hashMapOf(
                "calorie As plan" to calorie,
                "bmr" to bmr
            )
            dbref.child("user").child("user list").child(userid)
                .updateChildren(userMap as Map<String, Any>).addOnSuccessListener {
                    val intent = Intent(this, caloriebudget::class.java)
                    startActivity(intent)
                }
        }
        else if (activityLevel == 5){
            val TDEE = bmr*1.9
            val calorie = getCalorie(goalLevel, TDEE)
            val userMap = hashMapOf(
                "calorie As plan" to calorie,
                "bmr" to bmr
            )
            dbref.child("user").child("user list").child(userid)
                .updateChildren(userMap as Map<String, Any>).addOnSuccessListener {
                    val intent = Intent(this, caloriebudget::class.java)
                    startActivity(intent)
                }
        }
    }


    fun getCalorie(goalLevel: String, TDEE: Double): Double {
        if (goalLevel.equals("maintain"))
            return TDEE
        else if (goalLevel.equals("weightlose"))
            return TDEE - 0.20 * TDEE

        else if (goalLevel.equals("weightgain"))
            return TDEE + 0.20 * TDEE

        return 0.0
    }
}