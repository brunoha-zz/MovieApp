package br.com.brunoalmeida.movieapp.commons.model.entity

import com.google.gson.annotations.SerializedName


class MovieResponse(
        @SerializedName("Search")
        var movies: List<Movie>,
        @SerializedName("totalResults")
        var totalResults: Int
)