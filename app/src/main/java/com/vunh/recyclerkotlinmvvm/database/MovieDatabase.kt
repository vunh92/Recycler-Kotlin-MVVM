package com.vunh.recyclerkotlinmvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vunh.recyclerkotlinmvvm.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = createDatabase(context.applicationContext)
                    INSTANCE = instance
                }
                return instance
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "movie_db"
            ).fallbackToDestructiveMigration().build()
    }
}