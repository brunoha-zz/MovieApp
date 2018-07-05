package br.com.brunoalmeida.movieapp.commons.model.entity

import com.google.gson.annotations.SerializedName


open class MovieDetail() : Movie() {

    @SerializedName("Rated")
    val rated: String? = null
    @SerializedName("Released")
    val released: String? = null
    @SerializedName("Runtime")
    val runtime: String? = null
    @SerializedName("Genre")
    val genre: String? = null
    @SerializedName("Director")
    val Director: String? = null
    @SerializedName("Writer")
    val writer: String? = null
    @SerializedName("Actors")
    val actors: String? = null
    @SerializedName("Plot")
    val Plot: String? = null
    @SerializedName("Language")
    val language: String? = null
    @SerializedName("Country")
    val country: String? = null
    @SerializedName("Awards")
    val awards: String? = null
    @SerializedName("Metascore")
    val metascore: String? = null
    @SerializedName("imdbRating")
    val imdbRating: String? = null
    @SerializedName("imdbVotes")
    val imdbVotes: String? = null
    @SerializedName("DVD")
    val dVD: String? = null
    @SerializedName("BoxOffice")
    val boxOffice: String? = null
    @SerializedName("Production")
    val production: String? = null
    @SerializedName("Website")
    val website: String? = null
    @SerializedName("Response")
    val response: String? = null
}
