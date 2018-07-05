package br.com.brunoalmeida.movieapp.commons.network.services

import br.com.brunoalmeida.movieapp.commons.model.entity.MovieDetail
import br.com.brunoalmeida.movieapp.commons.model.entity.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(".")
    fun searchByName(@Query("s") search: String): Observable<MovieResponse>

    @GET(".")
    fun searchById(@Query("i") imdbID: String): Observable<MovieDetail>
}