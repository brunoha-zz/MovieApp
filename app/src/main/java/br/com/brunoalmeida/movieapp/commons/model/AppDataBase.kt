package br.com.brunoalmeida.movieapp.commons.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.brunoalmeida.movieapp.commons.model.dao.MovieDao
import br.com.brunoalmeida.movieapp.commons.model.entity.Movie
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration



@Database(entities = [Movie::class],version = 2, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
}

//val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL("ALTER TABLE movie " + " ADD COLUMN isFavorite INTEGER")
//    }
//}