package com.shrikant.cogniwide.ui.login

import android.os.Bundle
import com.shrikant.cogniwide.base.BaseActivity
import com.shrikant.cogniwide.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}