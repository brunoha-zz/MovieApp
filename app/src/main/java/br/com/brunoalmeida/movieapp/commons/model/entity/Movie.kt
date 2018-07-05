package br.com.brunoalmeida.movieapp.commons.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
open class Movie(
        @Expose(serialize = false)
        @PrimaryKey(autoGenerate = true)
        var id: Long?,
        @SerializedName("Title")
        var title: String?,
        @SerializedName("Year")
        var year: String?,
        @SerializedName("imdbID")
        var imdbID: String?,
        @SerializedName("Type")
        var type: String?,
        @SerializedName("Poster")
        var poster: String?,
        @Expose(serialize = false)
        var isFavorite : Boolean?
) {
    constructor() : this(null, "", "", "", "", "", false)

        override fun equals(other: Any?): Boolean {
                if(other !is Movie)
                     return false

                return this.imdbID == other.imdbID
        }
}
