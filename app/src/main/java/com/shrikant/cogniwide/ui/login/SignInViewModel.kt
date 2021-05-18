package com.shrikant.cogniwide.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.network.shared.core.result.Event
import com.network.shared.domain.exception.LoginFormState
import com.shrikant.cogniwide.base.BaseViewModel
import com.shrikant.cogniwide.utils.Utils
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor() :
    BaseViewModel() {

    private val _loginForm = MutableLiveData<Event<LoginFormState>>()
    val loginFormState: LiveData<Event<LoginFormState>> = _loginForm


    fun validateData(username: String, password: String) {
        viewModelScope.launch {
            var flag = true
            if (username.isEmpty()) {
                flag = false
            } else if (!Utils.isValidEmail(username)) {
                flag = false
            } else if (!isPasswordValid(password)) {
                flag = false
            }
            _loginForm.postValue(Event(LoginFormState(isDataValid = flag)))
        }
    }


    // A placeholder password validation checkCr
    private fun isPasswordValid(password: String): Boolean {
        return password.length in 6..12
    }

}
