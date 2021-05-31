package com.shrikant.cogniwide.ui.history

import com.network.shared.network.repository.HistoryRepository
import com.shrikant.cogniwide.base.BaseViewModel
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val repository: HistoryRepository
) :
    BaseViewModel() {

    val listObserver = repository.listObserver()


}
