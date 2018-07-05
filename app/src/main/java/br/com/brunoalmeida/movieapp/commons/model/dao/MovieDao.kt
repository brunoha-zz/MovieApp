package br.com.brunoalmeida.movieapp.commons.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import br.com.brunoalmeida.movieapp.commons.model.entity.Movie
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("Select * from movie")
    fun all() : Flowable<List<Movie>>

    @Query("Select * from movie")
    fun getMyMovies() : Single<List<Movie>>

    @Query("Select * from movie where title like :title")
    fun getMoviesByTitle(title : String) : Single<List<Movie>>

    @Query("Select * from movie where imdbID = :imdbId")
    fun getMovieByImdbId(imdbId : String) : Single<Movie>

    @Insert
    fun add(vararg movie : Movie)

    @Delete
    fun delete(vararg movie : Movie)
}