package com.example.recyclerview.data.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
@Parcelize
data class Ejemplo (
    @Json(name = "description")
    val description: String,
    @Json(name = "extension")
    val extension: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "modified")
    val modified: LocalDateTime = LocalDateTime.now(),
    @Json(name = "name")
    val name: String = "",
    @Json(name = "path")
    val path: String = "",
) : Parcelable