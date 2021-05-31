package com.shrikant.cogniwide.ui.history

import androidx.lifecycle.ViewModel
import com.network.shared.core.di.FragmentScoped
import com.network.shared.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Module where classes needed to create the [HistoryModule] are defined.
 */
@Module
internal abstract class HistoryModule {


    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragment(): HistoryFragment

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    internal abstract fun bindLoginViewModel(viewModel: HistoryViewModel): ViewModel


}
