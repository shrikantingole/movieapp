package com.shrikant.cogniwide.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.network.shared.core.result.Event
import com.network.shared.core.result.Results
import com.network.shared.domain.exception.movie.MovieListRes
import com.network.shared.domain.exception.movie.MovieResult
import com.network.shared.network.repository.MovieRepository
import com.shrikant.cogniwide.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
) :
    BaseViewModel() {

    private val _movieListObserver = MutableLiveData<Event<List<MovieResult>>>()
    val movieListObserver: LiveData<Event<List<MovieResult>>> = _movieListObserver


    fun callPopularMovieList() {
        loading.postValue(Event(true))
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.callMovieListAsync()) {
                is Results.Success -> handleSuccess(result.data)
                is Results.Error -> handleFailure(result.exception, result.code)
            }
            loading.postValue(Event(false))
        }
    }

    private fun handleSuccess(result: MovieListRes) {
        result.results?.let {
            _movieListObserver.postValue(Event(it))
        }
    }
}
