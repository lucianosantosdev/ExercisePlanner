package dev.lucianosantos.exerciseplanner.core.database.converters

import androidx.room.TypeConverter

class DaysOfWeekConverter {

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