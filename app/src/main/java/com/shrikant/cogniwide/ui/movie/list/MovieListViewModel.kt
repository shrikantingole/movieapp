package com.shrikant.cogniwide.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.network.shared.core.result.Event
import com.network.shared.core.result.Results
import com.network.shared.domain.video.VideoItem
import com.network.shared.domain.video.VideoListRes
import com.network.shared.network.repository.HistoryRepository
import com.network.shared.network.repository.MovieRepository
import com.shrikant.cogniwide.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val historyRepository: HistoryRepository
) :
    BaseViewModel() {

    private val _videoListObserver = MutableLiveData<Event<List<VideoItem>>>()
    val videoListObserver: LiveData<Event<List<VideoItem>>> = _videoListObserver


    fun callVideoList() {
        loading.postValue(Event(true))
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.callMovieListAsync()) {
                is Results.Success -> handleSuccess(result.data)
                is Results.Error -> handleFailure(result.exception, result.code)
            }
            loading.postValue(Event(false))
        }
    }

    private fun handleSuccess(result: VideoListRes) {
        result.results?.let {
            _videoListObserver.postValue(Event(it))
        }
    }

    fun insert(item: VideoItem) {
        historyRepository.insertDatabase(item)
    }
}
