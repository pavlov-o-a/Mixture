package com.mixture.packagecheck.di

import com.mixture.packagecheck.ui.PackageCheckFragment
import dagger.Component

@Component(modules = [DIModule::class])
@DIScope
interface DIComponent {

    fun inject(fragment: PackageCheckFragment)
}