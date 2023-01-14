package dev.lucianosantos.exerciseplanner.data

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromIntList(value: List<Int>): String = value.joinToString(separator = ",")

    @TypeConverter
    fun stringToIntList(string: String): List<Int> =
        if (string.isNotEmpty()) {
            string.split(",").map { it.toInt() }
        } else {
            listOf()
        }
}