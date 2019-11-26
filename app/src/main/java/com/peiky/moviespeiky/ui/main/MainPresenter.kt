package com.peiky.moviespeiky.ui.main


import com.peiky.moviespeiky.model.Movie
import com.peiky.moviespeiky.model.MoviesRepository
import com.peiky.moviespeiky.ui.util.Scope
import kotlinx.coroutines.launch

class MainPresenter(private  val moviesRepository: MoviesRepository): Scope by Scope.Impl() {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun updateData(movies: List<Movie>)
        fun navigateTo(movie: Movie)
    }

    private var view: View? = null


    fun onCreate(view: View) {
        initScope()
        this.view = view
        getData(1)
    }

    fun getData(page: Int) {

        launch {
            view?.showProgress()
            view?.updateData(moviesRepository.findMovies(page).results)
            view?.hideProgress()
        }
    }

    fun onMovieClicked(movie: Movie) {
        view?.navigateTo(movie)
    }

    fun onDestroy() {
        this.view = null
        destroyScope()
    }
}