package com.shrikant.cogniwide.ui.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.shrikant.cogniwide.base.BaseFragment
import com.shrikant.cogniwide.databinding.FragmentContainerBinding
import com.shrikant.cogniwide.ui.adapters.MovieListAdapter
import com.shrikant.cogniwide.ui.adapters.PagerAdapter
import javax.inject.Inject


class ContainerFragment : BaseFragment() {

    private lateinit var adapter: MovieListAdapter
    private lateinit var binding: FragmentContainerBinding
//    private lateinit var viewModel: ContainerModuleViewModel


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        viewModel = viewModelProvider(viewModelFactory)
        binding = FragmentContainerBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

    fun setAdapter(): Unit {
        val adapter = PagerAdapter(childFragmentManager, arrayOf("Videos", "History"))
        binding?.apply {
            vp.adapter = adapter
            tabLayout.setupWithViewPager(binding?.vp)
            tabLayout.isHorizontalScrollBarEnabled = false
            tabLayout.tabCount.let {
                for (i in 0 until it) {
                    val tab = tabLayout.getTabAt(i)
                    tab?.customView = adapter.getTabView(i, requireContext())
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }


}
