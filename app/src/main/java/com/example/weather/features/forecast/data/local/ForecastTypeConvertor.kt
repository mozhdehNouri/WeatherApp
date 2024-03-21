package com.example.weather.features.forecast.data.local

import androidx.room.TypeConverter

class ForecastTypeConvertor {
    @TypeConverter
    fun toListOfStrings(flatStringList: String): List<String> {
        return flatStringList.split(",")
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>): String {
        return listOfString.joinToString(",")
    }

    @TypeConverter
    fun toListOfDouble(flatDoubleList: String): List<Double> {
        return flatDoubleList.split("|") as List<Double>
    }

    @TypeConverter
    fun fromListOfDouble(listOfDouble: List<Double>): String {
        return listOfDouble.joinToString(separator = "|")
    }

    @TypeConverter
    fun toListOfInt(flatIntList: String): List<Int> {
        return flatIntList.split("/") as List<Int>
    }

    @TypeConverter
    fun fromListOfInt(listOfInt: List<Int>): String {
        return listOfInt.joinToString(separator = "/")
    }


}