package com.peiky.moviespeiky.model

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable

class DataSource(
    private val moviesServices: MoviesServices,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Movie>() {

    var state: MutableLiveData<Lifecycle.State> = MutableLiveData()
    private var retryCompletable: Completable? = null
    private val moviesRepository: MoviesRepository = MoviesRepository()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        //compositeDisposable.add(moviesRepository.findPopularMovies().results)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}