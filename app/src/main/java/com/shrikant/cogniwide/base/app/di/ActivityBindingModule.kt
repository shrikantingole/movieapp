package com.shrikant.cogniwide.base.app.di

import com.network.shared.core.di.ActivityScoped
import com.shrikant.cogniwide.ui.login.LoginActivity
import com.shrikant.cogniwide.ui.login.SignInModule
import com.shrikant.cogniwide.ui.movie.DashBoardActivity
import com.shrikant.cogniwide.ui.movie.list.MovieListModule
import com.shrikant.cogniwide.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module
 * ActivityBindingModule is on, in our case that will be [AppComponent]. You never
 * need to tell [AppComponent] that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that [AppComponent] exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the
 * specified modules and be aware of a scope annotation [@ActivityScoped].
 * When Dagger.Android annotation processor runs it will create 2 subcomponents for us.
 */
@Module
abstract class ActivityBindingModule {


    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
        ]
    )
    internal abstract fun splashActivity(): SplashActivity

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            SignInModule::class
        ]
    )
    internal abstract fun loginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            MovieListModule::class
        ]
    )
    internal abstract fun dashBoardActivity(): DashBoardActivity

}
