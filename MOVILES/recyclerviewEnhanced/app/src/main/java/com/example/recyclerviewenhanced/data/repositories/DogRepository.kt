package com.example.recyclerviewenhanced.data.repositories

import com.example.recyclerviewenhanced.data.model.DogResponse
import com.example.recyclerviewenhanced.data.model.toDog
import com.example.recyclerviewenhanced.data.sources.remote.RemoteDataSource
import com.example.recyclerviewenhanced.utils.NetworkResultt
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


@ActivityRetainedScoped
class DogRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getDog() =
        withContext(Dispatchers.IO)
        { remoteDataSource.getDog()}


}




