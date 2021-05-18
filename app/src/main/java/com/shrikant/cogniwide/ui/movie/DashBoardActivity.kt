package com.shrikant.cogniwide.ui.movie

import android.os.Bundle
import com.shrikant.cogniwide.base.BaseActivity
import com.shrikant.cogniwide.databinding.ActivityDashboardBinding

class DashBoardActivity : BaseActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}