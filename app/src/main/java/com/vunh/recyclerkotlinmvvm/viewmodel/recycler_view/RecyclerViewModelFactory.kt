package com.vunh.recyclerkotlinmvvm.viewmodel.recycler_view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vunh.recyclerkotlinmvvm.repository.RecyclerRepositoryImpl

class RecyclerViewModelFactory (
    private val recyclerRepositoryImpl: RecyclerRepositoryImpl
    ): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RecyclerViewModel::class.java)) {
            RecyclerViewModel(recyclerRepositoryImpl) as T
        }else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}