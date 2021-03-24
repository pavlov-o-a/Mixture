package com.mixture.packagecheck.di

import com.mixture.packagecheck.domain.IDChecker
import com.mixture.packagecheck.domain.IDCheckerImpl
import com.mixture.packagecheck.ui.PCViewModelProvider
import com.mixture.packagecheck.ui.PCViewModelProviderImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DIModule {

    @Binds
    abstract fun pcViewModuleProvider(pcViewModelProviderImpl: PCViewModelProviderImpl):
            PCViewModelProvider

    @Binds
    abstract fun packageChecker(checker: IDCheckerImpl): IDChecker
}