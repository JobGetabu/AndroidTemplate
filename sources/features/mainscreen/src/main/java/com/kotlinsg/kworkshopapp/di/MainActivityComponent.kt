package com.kotlinsg.kworkshopapp.di

import com.kotlinsg.kworkshopapp.MainActivity
import dagger.Component

@ActivityScope
@Component(
        dependencies = [ApplicationProvider::class],
        modules = [MainActivityModule::class])
interface MainActivityComponent {
    fun inject(activity: MainActivity)

    class Initializer private constructor() {
        companion object {
            fun init(appComponent: ApplicationProvider): MainActivityComponent =
                    DaggerMainActivityComponent.builder()
                            .applicationProvider(appComponent).build()
        }
    }
}

