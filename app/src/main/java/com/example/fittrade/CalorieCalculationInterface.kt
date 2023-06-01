package com.example.fittrade

import com.example.fittrade.dataModel.CalorieCalculationDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CalorieCalculationInterface {

    @GET("/macrocalculator")
    fun getData(@QueryMap queries: Map<String, Any>)
    : Call<CalorieCalculationDataResponse>
}