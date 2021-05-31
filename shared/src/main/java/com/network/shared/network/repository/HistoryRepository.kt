package com.network.shared.network.repository

import android.content.Context
import com.network.shared.database.AppDatabase
import com.network.shared.domain.video.VideoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    val context: Context,
    private val database: AppDatabase
) {

    private val jobList: ArrayList<Job> = arrayListOf()
    fun insertDatabase(videoItem: VideoItem) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            database.daoClass().insert(videoItem)
        }

        jobList.add(job)
    }

    fun listObserver() =/* MutableLiveData<VideoItem>() */database.daoClass().listofAll()

    suspend fun clearJob(): Unit {
        jobList.forEach {
            it.cancel(CancellationException("JobCancled"))
        }
    }
}
