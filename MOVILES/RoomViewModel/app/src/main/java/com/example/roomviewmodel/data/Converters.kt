package com.example.roomviewmodel.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.ZoneId


class Converters {

        @TypeConverter
        fun fromTimestamp(value: String?): LocalDate? {
            return value?.let { LocalDate.parse(it) }
        }


        @TypeConverter
        fun dateToTimestamp(date: LocalDate?): String? {
            return date?.toString()
        }
    }
