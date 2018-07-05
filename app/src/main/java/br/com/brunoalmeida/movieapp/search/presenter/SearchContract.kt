package br.com.brunoalmeida.movieapp.search.presenter

import br.com.brunoalmeida.movieapp.commons.model.entity.Movie
import br.com.brunoalmeida.movieapp.commons.mvp.BaseMvpPresenter
import br.com.brunoalmeida.movieapp.commons.mvp.BaseMvpView
import io.reactivex.Completable

interface SearchContract {

    interface View : BaseMvpView {

        fun buildList(movies : List<Movie>)

        fun refreshList()

        fun refreshItem()
    }

    interface Presenter : BaseMvpPresenter<View> {

        fun getMovies(name : String)

        fun saveMovie(movies : Movie)

        fun deleteMovie(movie : Movie)

    }
}