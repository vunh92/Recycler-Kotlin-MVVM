package com.vunh.recyclerkotlinmvvm

import android.app.Application
import com.vunh.recyclerkotlinmvvm.api.RecyclerService
import com.vunh.recyclerkotlinmvvm.database.MovieDatabase
import com.vunh.recyclerkotlinmvvm.repository.RecyclerRepositoryImpl

class BaseApp : Application() {
    companion object {
        var baseApp: BaseApp? = null
    }

    lateinit var recyclerService: RecyclerService
    lateinit var movieDatabase: MovieDatabase
    lateinit var recyclerRepositoryImpl: RecyclerRepositoryImpl

    override fun onCreate() {
        super.onCreate()
        baseApp = this
        recyclerService = RecyclerService.getInstance()
        movieDatabase = MovieDatabase.getInstance(this)
        recyclerRepositoryImpl = RecyclerRepositoryImpl(
            recyclerService,
            movieDatabase
        )
    }
}