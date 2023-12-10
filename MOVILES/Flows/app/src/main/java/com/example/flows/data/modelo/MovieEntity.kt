package com.example.flows.data.modelo

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.flows.domain.modelo.Movie

@Entity(tableName = "movie")
data class MovieEntity(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String?,
    val overview: String? = "",
    val popularity: Double = 0.0,
    val poster_path: String  = "",
//    val genre_ids: List<Int> = emptyList()
)

fun MovieEntity.toMovie() : Movie = Movie(this.title ?: "No title")

fun Movie.toMovieEntity(): MovieEntity = MovieEntity(title = this.titulo)
