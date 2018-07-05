package br.com.brunoalmeida.movieapp.moviedetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.brunoalmeida.movieapp.R
import br.com.brunoalmeida.movieapp.commons.RxBus
import br.com.brunoalmeida.movieapp.commons.model.entity.Movie
import br.com.brunoalmeida.movieapp.commons.model.entity.MovieDetail
import br.com.brunoalmeida.movieapp.commons.model.controllers.MovieController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val controller = MovieController()

        RxBus.behaviorSubject.subscribe {
            val movie: Movie = it as Movie

            controller.searchById(movie.imdbID!!)
                    .subscribe({ result ->
                        bindView(result)
                        Glide.with(applicationContext).load(result.poster).into(movie_detail_img)
//                        movie_detail_text.text = "Title: " + result.title + "Detail " + result.production
                    }, {
                        Log.d("---------->", it.message)
                    })
        }


    }

    private fun bindView(movie: MovieDetail) {
        movie_detail_title.text = movie.title
        movie_detail_year.text = movie.year
        movie_detail_actors.text = movie.actors
        movie_detail_director.text = movie.Director
        movie_detail_plot.text = movie.Plot
        movie_detail_production.text = movie.production
        movie_detail_released.text = movie.released
        movie_detail_year.text = movie.year
        movie_detail_imdb_note.text = movie.imdbRating
    }


}
