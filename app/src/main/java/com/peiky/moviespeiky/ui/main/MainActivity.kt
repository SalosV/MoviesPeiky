package com.peiky.moviespeiky.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peiky.moviespeiky.R
import com.peiky.moviespeiky.model.Movie
import com.peiky.moviespeiky.model.MoviesRepository
import com.peiky.moviespeiky.ui.detail.DetailActivity
import com.peiky.moviespeiky.ui.util.startActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val listMovies: RecyclerView by lazy { findViewById<RecyclerView>(R.id.list) }
    private val presenter : MainPresenter by lazy { MainPresenter(MoviesRepository()) }
    private val adapter = MoviesAdapter(presenter::onMovieClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onCreate(this)
        listMovies.layoutManager = LinearLayoutManager(this)
        listMovies.adapter = adapter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun updateData(movies: List<Movie>) {
        adapter.updataList(movies)
        adapter.notifyDataSetChanged()
    }

    override fun navigateTo(movie: Movie) {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.MOVIE, movie)
        }
    }

}

