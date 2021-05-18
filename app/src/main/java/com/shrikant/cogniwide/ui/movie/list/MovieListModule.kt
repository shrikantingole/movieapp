package com.shrikant.cogniwide.ui.movie.list

import androidx.lifecycle.ViewModel
import com.network.shared.core.di.FragmentScoped
import com.network.shared.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Module where classes needed to create the [MovieListModule] are defined.
 */
@Module
internal abstract class MovieListModule {


    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragment(): MovieListFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun bindLoginViewModel(viewModel: MovieListViewModel): ViewModel


}
