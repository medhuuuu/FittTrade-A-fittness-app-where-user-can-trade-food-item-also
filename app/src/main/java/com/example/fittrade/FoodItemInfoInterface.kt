package com.example.fittrade

import com.example.fittrade.dataModel.FoodItemInfoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodItemInfoInterface {
    @GET("/food")
    fun getFoodId(@Query("foodid") foodid : String) : Call<FoodItemInfoData>
}