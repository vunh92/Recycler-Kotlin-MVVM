package com.vunh.recyclerkotlinmvvm.repository

import androidx.lifecycle.LiveData
import com.vunh.recyclerkotlinmvvm.model.Movie
import com.vunh.recyclerkotlinmvvm.model.Response
import com.vunh.recyclerkotlinmvvm.model.Status
import com.vunh.recyclerkotlinmvvm.usecase.UseCaseResult

interface RecyclerRepository {
    suspend fun getMovieList(): UseCaseResult<List<Movie>>
    suspend fun getMovie(movieId: String): UseCaseResult<Response>
    suspend fun insert(movie: Movie) : UseCaseResult<Status>
    suspend fun update(movie: Movie) : UseCaseResult<Status>
    suspend fun delete(movieId: String) : UseCaseResult<Status>
    suspend fun clear()
    suspend fun insertAll(movies: List<Movie>)
    val movies: LiveData<List<Movie>>
}