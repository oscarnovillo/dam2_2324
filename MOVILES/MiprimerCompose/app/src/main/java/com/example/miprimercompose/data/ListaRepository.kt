package com.example.fistcompose.data

import com.example.fistcompose.domain.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class ListaRepository  @Inject constructor(

){


    fun getData() : Flow<List<Data>> =
        flow<List<Data>> {
            emit((1..100).map { i -> Data(i,"nombre $i") })
        }.flowOn(Dispatchers.IO)

}