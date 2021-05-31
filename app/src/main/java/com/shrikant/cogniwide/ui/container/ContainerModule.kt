package com.shrikant.cogniwide.ui.container

import com.network.shared.core.di.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module where classes needed to create the [ContainerModule] are defined.
 */
@Module
internal abstract class ContainerModule {


    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragment(): ContainerFragment

//    @Binds
//    @IntoMap
//    @ViewModelKey(ContainerModuleViewModel::class)
//    internal abstract fun bindLoginViewModel(viewModel: ContainerModuleViewModel): ViewModel


}
