package br.com.brunoalmeida.movieapp.mymovies.presenter

import br.com.brunoalmeida.movieapp.commons.RxBus
import br.com.brunoalmeida.movieapp.commons.model.controllers.MovieController
import br.com.brunoalmeida.movieapp.commons.model.entity.Movie
import br.com.brunoalmeida.movieapp.commons.mvp.BaseMvpPresenterImpl

class MyMoviePresenter : BaseMvpPresenterImpl<MyMovieContract.View>(), MyMovieContract.Presenter {


    private val controller = MovieController()

    override fun getMyMovies() {
        controller.getMyMovies().subscribe {movies->
            mView?.buildList(movies)
            mView?.dismissLoader()
        }
    }

    override fun searchMoviesByTitle(title: String) {
        if(title.isEmpty()){
            controller.getMyMovies().subscribe{movies->
                mView?.buildList(movies)
            }
        } else{
            controller.searcyMyMovieByTitle(title)?.subscribe {movies ->
                mView?.buildList(movies)
            }
        }
    }

    override fun deleteMovie(movie: Movie) {
        controller.deleteMovie(movie).subscribe {
            mView?.removeItem()
            mView?.showMessage("Filme deletado da sua lista")
        }
    }



}