package com.example.recyclerviewenhanced.data.sources.remote

import com.example.recyclerviewenhanced.data.model.BaseApiResponse
import com.example.recyclerviewenhanced.data.model.DogResponse
import com.example.recyclerviewenhanced.data.model.toDog
import com.example.recyclerviewenhanced.data.sources.remote.DogService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val dogService: DogService) : BaseApiResponse() {

    suspend fun getDog() = safeApiCall(apiCall = {dogService.getDog()}, transform = DogResponse::toDog)

}