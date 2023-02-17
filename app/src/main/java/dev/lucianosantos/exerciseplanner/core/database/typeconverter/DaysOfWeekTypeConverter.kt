package dev.lucianosantos.exerciseplanner.core.database.typeconverter

import androidx.room.TypeConverter

class DaysOfWeekTypeConverter {
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