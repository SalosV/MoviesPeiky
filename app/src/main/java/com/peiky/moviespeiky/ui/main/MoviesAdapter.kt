package com.peiky.moviespeiky.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peiky.moviespeiky.R
import com.peiky.moviespeiky.model.Movie
import com.peiky.moviespeiky.ui.util.loadUrl
import kotlinx.android.synthetic.main.card_movie.view.*


class MoviesAdapter(private val listener: (Movie) -> Unit): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var movies: List<Movie> = listOf()

    fun updataList(movies: List<Movie>) {
        this.movies = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_movie , parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {

            itemView.titleMovie.text = movie.originalTitle
            itemView.titleMovieTranslate.text = movie.title
            itemView.movieImage.loadUrl("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
        }
    }
}