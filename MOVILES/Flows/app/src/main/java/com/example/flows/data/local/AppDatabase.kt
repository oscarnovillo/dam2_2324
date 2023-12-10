package com.example.flows.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.flows.data.modelo.MovieEntity
import com.example.flows.data.GenreConverters

@Database(entities = [MovieEntity::class], version = 3)
//@TypeConverters(GenreConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}