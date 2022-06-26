package com.vunh.recyclerkotlinmvvm.viewmodel.detail_movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vunh.recyclerkotlinmvvm.repository.RecyclerRepositoryImpl

class DetailMovieViewModelFactory (
    private val recyclerRepositoryImpl: RecyclerRepositoryImpl
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailMovieViewModel::class.java)) {
            DetailMovieViewModel(recyclerRepositoryImpl) as T
        }else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}