package com.shrikant.cogniwide.base

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity : DaggerAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setAppOrientation()
        super.onCreate(savedInstanceState)
    }

    private fun setAppOrientation() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
    }

    fun hideSoftKeyBoard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (this.currentFocus != null) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
        }
    }

}