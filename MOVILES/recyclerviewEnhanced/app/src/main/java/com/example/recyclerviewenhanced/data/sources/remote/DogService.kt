package com.example.recyclerviewenhanced.data.sources.remote

import com.example.recyclerviewenhanced.utils.Constants
import com.example.recyclerviewenhanced.data.model.DogResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DogService {

    @GET(Constants.RANDOM_URL)
    suspend fun getDog(): Response<DogResponse>
}
