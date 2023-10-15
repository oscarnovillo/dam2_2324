package com.example.recyclerviewenhanced.data.model

import com.example.recyclerviewenhanced.domain.Dog
import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)

fun DogResponse.toDog() : Dog = Dog(message)
