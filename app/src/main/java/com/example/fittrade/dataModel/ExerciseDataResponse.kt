package com.example.fittrade.dataModel

class ExerciseDataResponse : ArrayList<ExerciseDataResponse.ExerciseDataResponseItem>(){
    data class ExerciseDataResponseItem(
        val Aka: String,
        val Category: String,
        val Difficulty: String,
        val Force: String,
        val Grips: String,
        val details: String,
        val exercise_name: String,
        val id: Int,
        val steps: List<String>,
        val target: Target,
        val videoURL: List<String>,
        val youtubeURL: String
    ){
        data class Target(
            val Primary: List<String>,
            val Secondary: List<String>,
            val Tertiary: List<String>
        )
    }
}