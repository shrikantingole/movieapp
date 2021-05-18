package com.shrikant.cogniwide.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.network.shared.core.result.Event
import com.network.shared.domain.exception.ErrorMessage

open class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Event<String>> = MutableLiveData()
    var loading: MutableLiveData<Event<Boolean>> = MutableLiveData()

    protected fun handleFailure(message: String) {
        this.failure.postValue(Event(message))
    }

    protected open fun handleFailure(body: Exception, code: Int) {
        if (body is ErrorMessage) {
            body.let {
                failure.postValue(Event(it.errors ?: "Error : $code"))
                return
            }
        }
        failure.postValue(Event(body.message.toString()))
    }
}