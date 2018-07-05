package br.com.brunoalmeida.movieapp

import android.app.Application
import android.arch.persistence.room.Room
import br.com.brunoalmeida.movieapp.commons.model.AppDataBase
import br.com.brunoalmeida.movieapp.commons.network.RetrofitClient


class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        retorfitClient = RetrofitClient()
        database = Room.databaseBuilder(
                this,
                AppDataBase::class.java,
                "movie-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
//                .addMigrations(MIGRATION_1_2)
                .build()

    }

    companion object {
        lateinit var database: AppDataBase
        lateinit var mInstance: MovieApp
        lateinit var retorfitClient: RetrofitClient

        fun <S> getService(service: Class<S>): S {
            return retorfitClient.createService(service)
        }
    }


}