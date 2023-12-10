package com.example.roomviewmodel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.roomviewmodel.data.modelo.CosaEntity
import com.example.roomviewmodel.data.modelo.PersonaEntity

@Database(entities = [PersonaEntity::class, CosaEntity::class], version =6, exportSchema = true)
@TypeConverters(Converters::class)
abstract class PersonaRoomDatabase : RoomDatabase() {

    abstract fun personaDao(): PersonaDao
    abstract fun cosaDao(): CosaDao

    companion object {
        @Volatile
        private var INSTANCE: PersonaRoomDatabase? = null

        fun getDatabase(context: Context): PersonaRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonaRoomDatabase::class.java,
                    "item_database"
                )
                    .createFromAsset("database/personas.db")
                    .fallbackToDestructiveMigrationFrom(4)
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}