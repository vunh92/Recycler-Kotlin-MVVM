package com.vunh.recyclerkotlinmvvm.viewmodel.create_movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vunh.recyclerkotlinmvvm.repository.RecyclerRepositoryImpl

class CreateMovieViewModelFactory(
    private val recyclerRepositoryImpl: RecyclerRepositoryImpl
    ) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CreateMovieViewModel::class.java)) {
            CreateMovieViewModel(recyclerRepositoryImpl) as T
        }else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}