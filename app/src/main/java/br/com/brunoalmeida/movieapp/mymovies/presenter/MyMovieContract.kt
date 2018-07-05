package br.com.brunoalmeida.movieapp.mymovies.presenter

import br.com.brunoalmeida.movieapp.commons.model.entity.Movie
import br.com.brunoalmeida.movieapp.commons.mvp.BaseMvpPresenter
import br.com.brunoalmeida.movieapp.commons.mvp.BaseMvpView

interface MyMovieContract {

    interface View : BaseMvpView {

        fun buildList(movies: List<Movie>)

        fun refreshList()

        fun removeItem()

        fun dismissLoader()


    }

    interface Presenter : BaseMvpPresenter<View> {
        fun getMyMovies()

        fun searchMoviesByTitle(title : String)


        fun deleteMovie(movie : Movie)
    }
}