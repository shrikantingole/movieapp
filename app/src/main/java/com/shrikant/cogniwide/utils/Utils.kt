package com.shrikant.cogniwide.utils

import android.text.TextUtils
import android.util.Patterns


object Utils {

    fun isValidEmail(email: String): Boolean {
        return if (!TextUtils.isEmpty(email)) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else false
    }

}
