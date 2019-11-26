package com.peiky.moviespeiky.ui.detail

import com.peiky.moviespeiky.model.Movie

class DetailPresenter {
    private var view: View? = null

    interface View {
        fun updateUI(movie: Movie)
    }

    fun onCreate(view: View, movie: Movie) {
        this.view = view
        view.updateUI(movie)
    }

    fun onDestroy() {
        view = null
    }
}