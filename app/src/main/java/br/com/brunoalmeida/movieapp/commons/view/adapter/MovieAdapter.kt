package br.com.brunoalmeida.movieapp.commons.view.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.brunoalmeida.movieapp.R
import br.com.brunoalmeida.movieapp.commons.model.entity.Movie
import br.com.brunoalmeida.movieapp.commons.network.downloadImage
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(
        val movies: List<Movie>, val context: Context
) : RecyclerView.Adapter<MovieViewHolder>() {

    var clickBus: PublishSubject<Movie> = PublishSubject.create()
    var favoriteClickBus: PublishSubject<Movie> = PublishSubject.create()
    var itemClickedBus: BehaviorSubject<Int> = BehaviorSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.bindView(movies[position])
        downloadImage(context.applicationContext, movies[position].poster, returnRequestListener(holder), holder.img)

        holder.itemView.setOnClickListener {
            clickBus.onNext(movies[position])
        }

        holder.like.setOnClickListener {
            movies[position].isFavorite = !holder.like.isChecked
            holder.like.isChecked = !holder.like.isChecked
            if (holder.like.isChecked) {
                holder.like.playAnimation()
            }

            itemClickedBus.onNext(position)
            favoriteClickBus.onNext(movies[position])
        }
    }
}

fun returnRequestListener(holder: MovieViewHolder): RequestListener<Drawable> {
    return object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            return false
        }

    }

}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val img = itemView.item_movie_img
    val text = itemView.item_movie_title
    val year = itemView.item_movie_year
    val like = itemView.item_movie_like


    fun bindView(movie: Movie) {
        text.text = movie.title
        year.text = movie.year
        like.isChecked = movie.isFavorite!!
    }
}

