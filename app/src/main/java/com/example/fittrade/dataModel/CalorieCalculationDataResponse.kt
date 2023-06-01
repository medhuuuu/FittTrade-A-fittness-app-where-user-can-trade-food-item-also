package com.example.fittrade.dataModel

data class CalorieCalculationDataResponse(
    val `data`: Data,
    val request_result: String,
    val status_code: Int
){
    data class Balanced(
        val carbs: Double,
        val fat: Double,
        val protein: Double
    )


    data class Data(
        val balanced: Balanced,
        val calorie: Double,
        val highprotein: Highprotein,
        val lowcarbs: Lowcarbs,
        val lowfat: Lowfat
    )

    data class Highprotein(
        val carbs: Double,
        val fat: Double,
        val protein: Double
    )

    data class Lowcarbs(
        val carbs: Double,
        val fat: Double,
        val protein: Double
    )

    data class Lowfat(
        val carbs: Double,
        val fat: Double,
        val protein: Double
    )
}