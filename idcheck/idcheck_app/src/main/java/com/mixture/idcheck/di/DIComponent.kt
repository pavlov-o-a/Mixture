package com.mixture.idcheck.di

import com.mixture.idcheck.ui.IdCheckFragment
import dagger.Component

@Component(modules = [DIModule::class])
@DIScope
interface DIComponent {

    fun inject(fragment: IdCheckFragment)
}