package com.peiky.moviespeiky.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
    private var visibleItemCount: Int = 0
    private var pastVisibleItemCount: Int = 0
    private var totalItemCount = 0
    private var loading: Boolean = true
    private var pageId: Int = 1

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
        adapter.updateList(movies)
        loading = true

        listMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    visibleItemCount = listMovies.layoutManager!!.childCount
                    totalItemCount = listMovies.layoutManager!!.itemCount
                    pastVisibleItemCount = (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()

                    if (loading) {
                        if (visibleItemCount + pastVisibleItemCount >= totalItemCount) {
                            loading = false
                            pageId++
                            presenter.getData(pageId)

                        }
                    }

                }

            }
        })

        if (movies.isNotEmpty()) {
            listMovies.scrollToPosition(0)
        }
    }

    override fun navigateTo(movie: Movie) {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.MOVIE, movie)
        }
    }

}

