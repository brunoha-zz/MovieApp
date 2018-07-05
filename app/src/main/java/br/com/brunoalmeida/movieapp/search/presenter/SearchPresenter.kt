package br.com.brunoalmeida.movieapp.search.presenter

import android.arch.persistence.room.EmptyResultSetException
import android.util.Log
import br.com.brunoalmeida.movieapp.commons.model.controllers.MovieController
import br.com.brunoalmeida.movieapp.commons.model.entity.Movie
import br.com.brunoalmeida.movieapp.commons.mvp.BaseMvpPresenterImpl
import timber.log.Timber

open class SearchPresenter : BaseMvpPresenterImpl<SearchContract.View>(), SearchContract.Presenter {

    private val controller = MovieController()

    override fun saveMovie(movie: Movie) {
        controller.allMyMovies().subscribe({ movies ->
            insertMovie(movie)
        }, {
            if (it is EmptyResultSetException) {
                insertMovie(movie)
            }
        })
    }

    fun insertMovie(movie: Movie) {
        controller.insertMovie(movie).subscribe {
            mView?.showMessage("Este filme foi salvo em seus vídeos")
            mView?.refreshItem()
            Log.w("inserido->", "${movie.title} 😎")
        }
    }

    override fun deleteMovie(movie: Movie) {
        controller.getMovieByImdbID(movie.imdbID!!).subscribe { deleteMovie ->
            controller.deleteMovie(deleteMovie).subscribe ({
                mView?.refreshList()
                mView?.showMessage("Este filme deletado dos seus vídeos")

                Timber.w("deletado->", "${movie.title} 😎")
            },{
                Timber.e(it)
            })
        }
    }

    override fun getMovies(name: String) {
        mView?.showLoading()
        controller.searchMovieByName(name)
                .subscribe({ movies ->
                    controller.getMyMovies().subscribe { myMovies ->
                        myMovies.forEach { myMovie ->
                            if (movies.contains(myMovie))
                                movies.forEach { movie ->
                                    if (movie.equals(myMovie))
                                        movie.isFavorite = true
                                }
                        }
                        mView?.buildList(movies)
                        mView?.dismissLoading()
                    }
                }, {
                    mView?.dismissLoading()
                    Log.d("Error ---> ", it.message)
                })
    }
}
