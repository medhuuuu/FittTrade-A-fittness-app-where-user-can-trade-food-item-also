package com.example.fittrade

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittrade.dataModel.FoodItemInfoData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class AllFoodItemsSearch : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var adapter: FoodItemAdapter
    private lateinit var foodlist : ArrayList<FoodItemInfoData>
    private lateinit var foodIdList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_food_items_search)

        searchView = findViewById(R.id.search_view)
        foodRecyclerView = findViewById(R.id.food_recycler)

        foodRecyclerView.setHasFixedSize(true)
        foodRecyclerView.layoutManager = LinearLayoutManager(this)

        foodlist = arrayListOf()
        foodIdList = arrayListOf("SR25_1_1", "SR25_1_2", "SR25_1_4", "SR25_1_4", "SR25_1_6", "SR25_1_7", "SR25_1_7", "SR25_1_7",
            "SR14_6_1", "SR14_6_2", "SR14_6_3", "SR14_6_4", "SR14_6_5", "SR14_6_6", "SR14_6_7", "SR14_6_8", "SR14_6_9", "SR14_6_10", "SR14_6_11",
            "SR25_1_10", "SR25_1_10", "SR25_1_10", "SR25_1_13", "SR25_1_14", "SR25_1_14", "SR14_1_1", "SR14_1_2", "SR14_1_3", "SR14_1_4",
            "SR14_6_12", "SR14_6_13", "SR14_6_14", "SR14_6_15", "SR14_6_16", "SR14_6_17", "SR14_6_18", "SR14_6_19", "SR14_6_20", "SR14_6_21",
            "SR14_1_5", "SR14_4_1", "SR14_4_2", "SR14_2_2", "SR14_2_3", "SR14_2_4", "SR14_2_5", "SR14_2_6", "SR15_5_1","SR15_5_2","SR15_5_3",
            "SR14_6_22", "SR14_6_23", "SR14_6_24", "SR14_6_25", "SR14_6_26", "SR14_6_27", "SR14_6_28", "SR14_6_29", "SR14_6_30", "SR14_6_31",
            "SR14_6_32", "SR14_6_33", "SR14_6_34","SR14_6_35", "SR14_6_36","SR14_6_37","SR14_6_38","SR14_6_39","SR14_6_40"
    )

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })


        getData()
    }

    private fun filterList(query: String?) {

        if (query!= null){
            val filteredList = ArrayList<FoodItemInfoData>()
            foodlist.forEach {
                if (it.data.description.lowercase(Locale.ROOT).contains(query)){
                    filteredList.add(it)
                }
            }

            if (filteredList.isEmpty()){
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
            }
            else{
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun getData() {

        GlobalScope.launch(Dispatchers.IO) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://fitness-calculator.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader(
                            "X-RapidAPI-Key",
                            "75ef70a478msh3da1c20824c8e15p13575ejsn26185c236f61"
                        )
                        .build()
                    chain.proceed(newRequest)
                }.build())
                .build()

            val retrofitData = retrofit.create(FoodItemInfoInterface::class.java)

            foodIdList.forEach {
                val response = retrofitData.getFoodId(it).awaitResponse()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        val responseBody = response.body()
                        foodlist.add(responseBody!!)
                        adapter = FoodItemAdapter(baseContext, foodlist)
                    }
                    foodRecyclerView.adapter = adapter
                }
            }


//            val response = retrofitData.getFoodId("SR25_1_1").awaitResponse()
//            Log.d("Response", response.toString() + " " + response.body().toString())
//            withContext(Dispatchers.Main){
//                if(response.body() != null){
//                    val responseBody = response.body()!!

//                    responseBody.forEach {
//                        foodlist.add(it)
//                        adapter = FoodItemAdapter(baseContext, foodlist)
//                    }
//                    foodRecyclerView.adapter = adapter


//        }
//    }
        }




    }
}