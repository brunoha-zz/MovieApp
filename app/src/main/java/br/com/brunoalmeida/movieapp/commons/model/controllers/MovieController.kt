package br.com.brunoalmeida.movieapp.commons.model.controllers

import android.arch.persistence.room.EmptyResultSetException
import android.support.v7.widget.DialogTitle
import br.com.brunoalmeida.movieapp.MovieApp
import br.com.brunoalmeida.movieapp.commons.RxBus
import br.com.brunoalmeida.movieapp.commons.model.entity.Movie
import br.com.brunoalmeida.movieapp.commons.model.entity.MovieDetail
import br.com.brunoalmeida.movieapp.commons.network.services.MovieService
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MovieController {

    private val movieService = MovieApp.getService(MovieService::class.java)
    private val database = MovieApp.database

    fun searchMovieByName(name: String): Observable<List<Movie>> {
        return movieService.searchByName(name).doOnError {
            RxBus.errorSubject.onNext(it)
        }.map {
            it.movies
        }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())

    }

    fun searcyMyMovieByTitle(title: String): Single<List<Movie>>? {
        return database.movieDao().getMoviesByTitle("%$title%")
                .doOnError {
                    RxBus.errorSubject.onNext(it)
                }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    fun searchById(imdbId: String): Observable<MovieDetail> {
        return movieService.searchById(imdbId).doOnError {
            RxBus.errorSubject.onNext(it)
        }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    fun getMyMovies(): Flowable<List<Movie>> {
        return database.movieDao().all().doOnError {
            RxBus.errorSubject.onNext(it)
        }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    fun allMyMovies() : Single<List<Movie>> {
        return database.movieDao().getMyMovies().doOnError {
            RxBus.errorSubject.onNext(it)
        }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    fun insertMovie(movie: Movie): Completable {
        return Completable.fromAction {
            database.movieDao().add(movie)
        }.doOnError {
            RxBus.errorSubject.onNext(it)
        }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())

    }

    fun getMovieByImdbID(id: String): Single<Movie> {
        return database.movieDao().getMovieByImdbId(id)
                .doOnError {
                    RxBus.errorSubject.onNext(it)
                }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

    fun deleteMovie(movie: Movie): Completable {
        return Completable.fromAction {
            database.movieDao().delete(movie)
        }.doOnError {
            RxBus.errorSubject.onNext(it)
        }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }


}
