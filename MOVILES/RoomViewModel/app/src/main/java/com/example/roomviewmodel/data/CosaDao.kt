package com.example.roomviewmodel.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomviewmodel.data.modelo.CosaEntity

@Dao
interface CosaDao {



    @Query("SELECT * from cosas")
    suspend fun getCosas(): List<CosaEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: CosaEntity)

}