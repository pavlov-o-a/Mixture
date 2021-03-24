package com.mixture.idcheck.di

import com.mixture.idcheck.ui.PackageCheckFragment
import dagger.Component

@Component(modules = [DIModule::class])
@DIScope
interface DIComponent {

    fun inject(fragment: PackageCheckFragment)
}