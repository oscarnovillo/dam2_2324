package com.example.flows.data



import com.example.flows.BuildConfig
import com.example.flows.data.local.MovieDao
import com.example.flows.data.modelo.MovieDesc
import com.example.flows.data.modelo.TrendingMovieResponse
import com.example.flows.data.modelo.toMovie
import com.example.flows.data.modelo.toMovieEntity
import com.example.flows.data.remote.MovieRemoteDataSource
import com.example.flows.domain.modelo.Movie
import com.example.flows.framework.utils.Utils
import com.example.flows.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repository which fetches data from Remote or Local data sources
 */
class MovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieDao: MovieDao
) {

    fun fetchTrendingMovies(): Flow<NetworkResult<List<Movie>>> {
        return flow {
            emit(fetchTrendingMoviesCached())
            emit(NetworkResult.Loading())
            val result = movieRemoteDataSource.fetchTrendingMovies()
            emit(result)
            //Cache to database if response is successful
            if (result is NetworkResult.Success) {
                result.data?.let { it ->
                    movieDao.deleteAll(it.map{ it.toMovieEntity()})
                    movieDao.insertAll(it.map{ it.toMovieEntity()})
                }
            }

        }.flowOn(Dispatchers.IO)
    }

    private fun fetchTrendingMoviesCached(): NetworkResult<List<Movie>> =
            movieDao.getAll().let {list->
                NetworkResult.Success(list.map { it.toMovie() } ?: emptyList())
            }

    fun fetchMovie(id: Int): Flow<NetworkResult<MovieDesc>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(movieRemoteDataSource.fetchMovie(id))
        }.flowOn(Dispatchers.IO)
    }
}