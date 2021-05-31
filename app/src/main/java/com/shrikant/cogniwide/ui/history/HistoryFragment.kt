package com.shrikant.cogniwide.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.network.shared.core.result.EventObserver
import com.network.shared.util.viewModelProvider
import com.shrikant.cogniwide.base.BaseFragment
import com.shrikant.cogniwide.databinding.FragmentHistoryBinding
import com.shrikant.cogniwide.ui.adapters.HistoryListAdapter
import javax.inject.Inject


class HistoryFragment : BaseFragment() {

    private lateinit var adapter: HistoryListAdapter
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var viewModel: HistoryViewModel


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = viewModelProvider(viewModelFactory)
        binding = FragmentHistoryBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter()
        viewModel.listObserver.observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setItemList(it)
            }
        })
        viewModel.loading.observe(viewLifecycleOwner, EventObserver {
            binding.progreeBar.isVisible = it
        })
        viewModel.failure.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setAdapter() {
        adapter = HistoryListAdapter()
        binding.rvList.adapter = adapter
    }

}
