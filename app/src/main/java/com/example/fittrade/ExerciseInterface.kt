package com.example.fittrade

import com.example.fittrade.dataModel.ExerciseDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExerciseInterface {

    @GET("/exercises")
    fun getData(@Query("category") filter1: String?): Call<ExerciseDataResponse>

}