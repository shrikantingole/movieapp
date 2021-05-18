package com.shrikant.cogniwide.ui.movie.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.network.shared.core.result.EventObserver
import com.network.shared.util.viewModelProvider
import com.shrikant.cogniwide.base.BaseFragment
import com.shrikant.cogniwide.databinding.FragmentMovieListBinding
import com.shrikant.cogniwide.ui.adapters.MovieListAdapter
import javax.inject.Inject


class MovieListFragment : BaseFragment() {

    private lateinit var adapter: MovieListAdapter
    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel: MovieListViewModel


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = viewModelProvider(viewModelFactory)
        binding = FragmentMovieListBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter()
        viewModel.movieListObserver.observe(viewLifecycleOwner, EventObserver {
            adapter.setItemList(it)
        })
        viewModel.loading.observe(viewLifecycleOwner, EventObserver {
            binding.progreeBar.isVisible = it
        })
        viewModel.failure.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.callPopularMovieList()
    }

    private fun setAdapter() {
        binding.rvList.layoutManager = GridLayoutManager(requireActivity(), 2)
        adapter = MovieListAdapter()
        binding.rvList.adapter = adapter
    }

}
