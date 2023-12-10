package com.example.flows.data.local

import androidx.room.*
import com.example.flows.data.modelo.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie order by popularity DESC")
    fun getAll(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieEntity>)

    @Delete
    fun delete(movie: MovieEntity)

    @Delete
    fun deleteAll(movie: List<MovieEntity>)
}