package com.shrikant.cogniwide.ui.login

import androidx.lifecycle.ViewModel
import com.network.shared.core.di.FragmentScoped
import com.network.shared.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Module where classes needed to create the [SignInModule] are defined.
 */
@Module
internal abstract class SignInModule {


    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeSignInFragment(): SignInFragment

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    internal abstract fun bindLoginViewModel(viewModel: SignInViewModel): ViewModel


}
