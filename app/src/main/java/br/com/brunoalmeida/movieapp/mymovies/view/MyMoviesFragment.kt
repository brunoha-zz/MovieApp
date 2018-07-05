package br.com.brunoalmeida.movieapp.mymovies.view

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
import br.com.brunoalmeida.movieapp.mymovies.presenter.MyMovieContract
import br.com.brunoalmeida.movieapp.mymovies.presenter.MyMoviePresenter
import kotlinx.android.synthetic.main.my_movies_fragment.*


class MyMoviesFragment : BaseMvpFragment<MyMovieContract.View, MyMoviePresenter>(), MyMovieContract.View {

    override fun dismissLoader() {
        my_movies_refresh.isRefreshing = false

    }

    lateinit var movieAdapter: MovieAdapter
    override var mPresenter: MyMoviePresenter = MyMoviePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.my_movies_fragment, container, false)
        mPresenter.getMyMovies()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        my_movies_refresh.setOnRefreshListener {
            mPresenter.getMyMovies()
        }
        disposable = RxBus.searchSubject.subscribe { query ->
            mPresenter.searchMoviesByTitle(query)

        }
    }

    override fun buildList(movies: List<Movie>) {
        movieAdapter = MovieAdapter(movies, context)
        if(movies.isEmpty()){
            text_tuto.visibility = View.VISIBLE
            my_movies_recycler.visibility = View.GONE
        } else {
            text_tuto.visibility = View.GONE
            my_movies_recycler.visibility = View.VISIBLE
        }

        my_movies_recycler.adapter = movieAdapter
        my_movies_recycler.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)

        movieAdapter.clickBus.subscribe { item ->
            RxBus.behaviorSubject.onNext(item)
            startActivity(Intent(context, MovieDetailActivity::class.java))
        }

        movieAdapter.favoriteClickBus.subscribe {
            mPresenter.deleteMovie(it)
        }

    }

    override fun removeItem() {
        movieAdapter.itemClickedBus.subscribe {
            movieAdapter.notifyItemRemoved(it)
            movieAdapter.notifyItemRangeChanged(it,movieAdapter.itemCount )
        }
    }

    override fun refreshList() {
        my_movies_recycler.adapter.notifyDataSetChanged()
    }

//
}


