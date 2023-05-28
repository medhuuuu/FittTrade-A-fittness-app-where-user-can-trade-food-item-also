package com.example.fittrade.dataModel

class FoodItemInfoData(
    val `data`: Data,
    val request_result: String,
    val status_code: Int,
) {
    data class Data(
        val description: String,
        val foodNutrients: FoodNutrients,
        val foodid: String,
        val portion: Double,
        val portionUnit: String
    ){

        data class Ash(
            val unitname: String,
            val value: Double
        )

        data class Caffeine(
            val unitname: String,
            val value: Int
        )

        data class CalciumCa(
            val unitname: String,
            val value: Int
        )

        data class CopperCu(
            val unitname: String,
            val value: Double
        )

        data class CholineTotal(
            val unitname: String,
            val value: Double
        )

        data class Carbonhydrate(
            val unitname: String,
            val value: Double
        )

        data class Data(
            val description: String,
            val foodNutrients: FoodNutrients,
            val foodid: String,
            val portion: Double,
            val portionUnit: String
        )

        data class Energy(
            val unitname: String,
            val value: Int
        )

        data class Fat(
            val fattyAcidsTotalMonounsaturated: FattyAcidsTotalMonounsaturated,
            val fattyAcidsTotalPolyunsaturated: FattyAcidsTotalPolyunsaturated,
            val fattyAcidsTotalSaturated: FattyAcidsTotalSaturated,
            val totalLipidFat: TotalLipidFat
        )

        data class FattyAcidsTotalMonounsaturated(
            val unitname: String,
            val value: Double
        )

        data class FattyAcidsTotalPolyunsaturated(
            val unitname: String,
            val value: Double
        )

        data class FattyAcidsTotalSaturated(
            val unitname: String,
            val value: Double
        )

        data class FiberTotalDietary(
            val unitname: String,
            val value: Double
        )

        data class FolateDFE(
            val unitname: String,
            val value: Int
        )

        data class FolateFood(
            val unitname: String,
            val value: Int
        )

        data class FolateTotal(
            val unitname: String,
            val value: Int
        )

        data class FoodNutrients(
            val Carbonhydrate: Carbonhydrate,
            val Energy: Energy,
            val Fat: Fat,
            val Micronutrients: Micronutrients,
            val Protein: Protein,
            val Water: Water
        )

        data class IronFe(
            val unitname: String,
            val value: Double
        )

        data class LuteinZeaxanthin(
            val unitname: String,
            val value: Int
        )

        data class MagnesiumMg(
            val unitname: String,
            val value: Int
        )

        data class ManganeseMn(
            val unitname: String,
            val value: Double
        )

        data class Micronutrients(
            val ash: Ash,
            val caffeine: Caffeine,
            val calciumCa: CalciumCa,
            val cholineTotal: CholineTotal,
            val copperCu: CopperCu,
            val fiberTotalDietary: FiberTotalDietary,
            val folateDFE: FolateDFE,
            val folateFood: FolateFood,
            val folateTotal: FolateTotal,
            val ironFe: IronFe,
            val luteinZeaxanthin: LuteinZeaxanthin,
            val mufa181: MUFA181,
            val magnesiumMg: MagnesiumMg,
            val manganeseMn: ManganeseMn,
            val niacin: Niacin,
            val pufa182: PUFA182,
            val pufa183: PUFA183,
            val pantothenicAcid: PantothenicAcid,
            val phosphorusP: PhosphorusP,
            val potassiumK: PotassiumK,
            val riboflavin: Riboflavin,
            val sfa140_1: SFA140,
            val sfa140_2: SFA140,
            val sfa180: SFA140,
            val seleniumSe: SeleniumSe,
            val sodiumNa: SodiumNa,
            val sugarsTotalIncludingNLEA: SugarsTotalIncludingNLEA,
            val theobromine: Theobromine,
            val thiamin: Thiamin,
            val vitaminB6: VitaminB6,
            val vitaminCTotalAscorbicAcid: VitaminCTotalAscorbicAcid,
            val vitaminEAlphaTocopherol: VitaminEAlphaTocopherol,
            val vitaminKPhylloquinone: VitaminKPhylloquinone,
            val zincZn: ZincZn
        )

        data class MUFA181(
            val unitname: String,
            val value: Double
        )

        data class Niacin(
            val unitname: String,
            val value: Double
        )

        data class PantothenicAcid(
            val unitname: String,
            val value: Double
        )

        data class PhosphorusP(
            val unitname: String,
            val value: Int
        )

        data class PotassiumK(
            val unitname: String,
            val value: Int
        )

        data class Protein(
            val unitname: String,
            val value: Double
        )

        data class PUFA182(
            val unitname: String,
            val value: Double
        )

        data class PUFA183(
            val unitname: String,
            val value: Double
        )

        data class Riboflavin(
            val unitname: String,
            val value: Double
        )

        data class SeleniumSe(
            val unitname: String,
            val value: Double
        )

        data class SFA140(
            val unitname: String,
            val value: Double
        )

        data class SodiumNa(
            val unitname: String,
            val value: Int
        )

        data class SugarsTotalIncludingNLEA(
            val unitname: String,
            val value: Double
        )

        data class Theobromine(
            val unitname: String,
            val value: Int
        )

        data class Thiamin(
            val unitname: String,
            val value: Double
        )

        data class TotalLipidFat(
            val unitname: String,
            val value: Double
        )

        data class VitaminB6(
            val unitname: String,
            val value: Double
        )

        data class VitaminCTotalAscorbicAcid(
            val unitname: String,
            val value: Double
        )

        data class VitaminEAlphaTocopherol(
            val unitname: String,
            val value: Double
        )

        data class VitaminKPhylloquinone(
            val unitname: String,
            val value: Double
        )

        data class Water(
            val unitname: String,
            val value: Double
        )

        data class ZincZn(
            val unitname: String,
            val value: Double
        )

    }
}