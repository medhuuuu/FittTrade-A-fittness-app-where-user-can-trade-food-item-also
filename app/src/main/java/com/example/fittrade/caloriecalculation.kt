package com.example.fittrade

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

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
    private lateinit var calcuView: TextView

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
        calcuView = findViewById(R.id.calculateView)
        calcuBtn = findViewById(R.id.calculateBtn)

        val userid = FirebaseAuth.getInstance().currentUser!!.uid
        dbref = Firebase.database.reference


        val activityLevelNames = arrayOf("1:BMR", "2:Sedentary: little or no exercise", "3:Exercise 1-3 times/week",
            "4:Exercise 4-5 times/week", "5:Daily exercise or intense exercise 3-4 times/week",
            "6:Intense exercise 6-7 times/week", "7:Very intense exercise daily, or physical job")

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


        val goals = arrayOf("maintain", "mildlose", "weightlose", "extremelose", "mildgain", "weightgain", "extremegain")

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
                TODO("Not yet implemented")
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

                val activityLevel = activityText.text.toString()
                val activityLevelInt = activityLevel.toInt()

                val goalLevel = goalTextView.text.toString()

                calcuBtn.setOnClickListener {
                    getData(ageNum, gender, heightNum, weightNum, activityLevelInt, goalLevel)

                }

            }

        }

    }

    private fun getData(ageNum: Int, gender: String, heightNum: Double, weightNum: Double, activityLevel: Int, goalLevel: String) {
        GlobalScope.launch(Dispatchers.IO){
            try {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://fitness-calculator.p.rapidapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient.Builder().addInterceptor { chain ->
                        val newRequest = chain.request().newBuilder()
                            .addHeader(
                                "X-RapidAPI-Key",
                                "75ef70a478msh3da1c20824c8e15p13575ejsn26185c236f61"
                            ).build()
                        chain.proceed(newRequest)
                    }.build())
                    .build()

                val retrofitData = retrofit.create(CalorieCalculationInterface::class.java)

                val queries = HashMap<String, Any>()
                queries.put("age", ageNum)
                queries.put("gender", gender)
                queries.put("height", heightNum)
                queries.put("weight", weightNum)
                queries.put("activitylevel", activityLevel)
                queries.put("goal", goalLevel)

                val response = retrofitData.getData(queries).awaitResponse()
                withContext(Dispatchers.Main){
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        val calculate = responseBody?.data?.calorie.toString()
                        calcuView.text = calculate

                    }
                }
            } catch (e: Exception) {

            }
        }


    }
}