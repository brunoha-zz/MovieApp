@file:Suppress("DIFFERENT_NAMES_FOR_THE_SAME_PARAMETER_IN_SUPERTYPES")

package br.com.brunoalmeida.movieapp.search.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.brunoalmeida.movieapp.R
import br.com.brunoalmeida.movieapp.commons.RxBus
import br.com.brunoalmeida.movieapp.commons.model.entity.Movie
import br.com.brunoalmeida.movieapp.commons.mvp.BaseMvpFragment
import br.com.brunoalmeida.movieapp.commons.view.adapter.MovieAdapter
import br.com.brunoalmeida.movieapp.moviedetail.MovieDetailActivity
import br.com.brunoalmeida.movieapp.search.presenter.SearchContract
import br.com.brunoalmeida.movieapp.search.presenter.SearchPresenter
import kotlinx.android.synthetic.main.search_fragment.*


class SearchFragment : BaseMvpFragment<SearchContract.View, SearchPresenter>(), SearchContract.View {

    override var mPresenter: SearchPresenter = SearchPresenter()
    lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun refreshItem() {
        movieAdapter.itemClickedBus.subscribe { i ->
            movieAdapter.notifyItemChanged(i)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disposable = RxBus.searchSubject.subscribe { query ->
            mPresenter.getMovies(query)
        }
    }

    override fun buildList(movies: List<Movie>) {
        movieAdapter = MovieAdapter(movies, context)
        search_recycler.adapter = movieAdapter
        search_recycler.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)

        movieAdapter.clickBus.subscribe { item ->
            RxBus.behaviorSubject.onNext(item)
            startActivity(Intent(activity, MovieDetailActivity::class.java))
        }

        movieAdapter.favoriteClickBus.subscribe { movie ->
            if (movie.isFavorite!!)
                mPresenter.saveMovie(movie)
            else
                mPresenter.deleteMovie(movie)
        }
    }

    override fun refreshList() {
        movieAdapter.notifyDataSetChanged()
    }

    override fun onStop() {
        super.onStop()
        disposable.dispose()
    }
//

}


