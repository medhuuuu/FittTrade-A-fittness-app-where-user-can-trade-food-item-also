package com.example.fittrade

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittrade.dataModel.ExerciseDataResponse
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(DelicateCoroutinesApi::class)
class ExercisesActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ExerciseAdapter
    private lateinit var exrList: ArrayList<ExerciseDataResponse.ExerciseDataResponseItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excercises)

        recyclerView = findViewById(R.id.reycle_excercise)
        exrList = arrayListOf()

        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager

        val type = intent.getStringExtra("type")
        val bmi = intent.getStringExtra("bmi")?.toInt()
        val gender = intent.getStringExtra("gender")



        getData(type!!, gender!!, bmi!!)

    }


    private fun getData(type : String, gender : String, bmi : Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://musclewiki.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader("X-RapidAPI-Key", "75ef70a478msh3da1c20824c8e15p13575ejsn26185c236f61")
                        .build()
                    chain.proceed(newRequest)
                }.build())
                .build()

            val retrofitData = retrofit.create(ExerciseInterface ::class.java)

            if (type == "Yoga"){
                val response = retrofitData.getData(type).awaitResponse()
                withContext(Dispatchers.Main){
                    if(response.body() != null){
                        val responseBody = response.body()!!

                        responseBody.forEach {
                            if (it.Category == type){
                                exrList.add(it)
                                adapter = ExerciseAdapter(baseContext, exrList)
                            }
                        }
                        recyclerView.adapter = adapter
                    }
                }
            }
            else{
                val response = retrofitData.getData("Bodyweight").awaitResponse()
                withContext(Dispatchers.Main){
                    if(response.body() != null){
                        val responseBody = response.body()!!

                        responseBody.forEach {

                            val primary = it.target.Primary?.contains(type) ?: false
                            val secondary = it.target.Secondary?.contains(type) ?: false
                            val tertiary = it.target.Tertiary?.contains(type) ?: false


                            if (it.Difficulty == type || primary!! || secondary!! || tertiary!!){
                                exrList.add(it)
                                adapter = ExerciseAdapter(baseContext, exrList)

                            }
                        }

                        recyclerView.adapter = adapter

                    }

                }
            }





        }

    }

}



//        retrofitData.getData().enqueue(object : Callback<ExcerciseResponseData?> {
//            override fun onResponse(
//                call: Call<ExcerciseResponseData?>,
//                response: Response<ExcerciseResponseData?>
//            ) {
//                    if (response.isSuccessful){
//                        val responseBody = response.body()
//
//                        println(responseBody)
//
//
//                        binding.name.text = responseBody?.exercise_name
//                    }
//            }
//
//            override fun onFailure(call: Call<ExcerciseResponseData?>, t: Throwable) {
//
//            }
//        })
