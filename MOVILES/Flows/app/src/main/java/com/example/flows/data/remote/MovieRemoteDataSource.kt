package com.example.flows.data.remote

import com.example.flows.data.modelo.MovieDesc
import com.example.flows.data.modelo.TrendingMovieResponse
import com.example.flows.data.modelo.toMovie
import com.example.flows.domain.modelo.Movie
import com.example.flows.utils.NetworkResult
import io.buildwithnd.demotmdb.network.services.MovieService
import javax.inject.Inject

/**
 * fetches data from remote source
 */
class MovieRemoteDataSource @Inject constructor(private val movieService: MovieService) :
    BaseApiResponse() {

    suspend fun fetchTrendingMovies(): NetworkResult<List<Movie>> {

        return safeApiCall(apiCall = {movieService.getPopularMovies()},
            transform = { trendingMovieResponse -> trendingMovieResponse
                .results?.map { movieEntity ->  movieEntity.toMovie()} ?: emptyList()})

    }

    suspend fun fetchMovie(id: Int): NetworkResult<MovieDesc> {

        return safeApiCall(apiCall = {movieService.getMovie(id)})

    }

//    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {
//        return try {
//            println("I'm working in thread ${Thread.currentThread().name}")
//            val result = request.invoke()
//            if (result.isSuccessful) {
//                return Result.success(result.body())
//            } else {
//                val errorResponse = ErrorUtils.parseError(result, retrofit)
//                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
//            }
//        } catch (e: Throwable) {
//            Result.error("Unknown Error", null)
//        }
//    }
}