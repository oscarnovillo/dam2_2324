package com.example.flows.framework.utils



sealed class Result<T>(
    var data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : Result<T>(data)

    class Error<T>(message: String, data: T? = null) : Result<T>(data, message)

    class Loading<T> : Result<T>()


    fun <R> map( transform :(data: T?) -> R) : Result<R> =
        when(this){
            is Error -> Error(message!!,transform(data))
            is Loading -> Loading()
            is Success -> Success(transform(data))
        }




}