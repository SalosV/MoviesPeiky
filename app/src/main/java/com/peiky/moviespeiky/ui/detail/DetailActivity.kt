package com.peiky.moviespeiky.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.peiky.moviespeiky.R
import com.peiky.moviespeiky.model.Movie
import com.peiky.moviespeiky.ui.util.loadUrl
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailPresenter.View {

    companion object {
        const val MOVIE = "DetailActivity:movie"
    }

    private val presenter = DetailPresenter()
    private val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    private val description: TextView by lazy { findViewById<TextView>(R.id.description) }
    private val poster: ImageView by lazy { findViewById<ImageView>(R.id.poster) }
    private val voteRating: RatingBar by lazy { findViewById<RatingBar>(R.id.voteRating)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        
        settingToolbar()
        
        presenter.onCreate(this, intent.getParcelableExtra(MOVIE))
    }

    private fun settingToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    override fun updateUI(movie: Movie) {
        title = movie.title
        poster.loadUrl("https://image.tmdb.org/t/p/w185/${movie.backdropPath}")
        description.text = movie.overview
        voteRating.rating = movie.voteAverage.toFloat()
    }
}
