package com.mixture.idcheck.di

import com.mixture.idcheck.domain.IDChecker
import com.mixture.idcheck.domain.IDCheckerImpl
import com.mixture.idcheck.ui.IdCheckViewModelProvider
import com.mixture.idcheck.ui.IdCheckViewModelProviderImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DIModule {

    @Binds
    abstract fun pcViewModuleProvider(idCheckViewModelProviderImpl: IdCheckViewModelProviderImpl):
            IdCheckViewModelProvider

    @Binds
    abstract fun packageChecker(checker: IDCheckerImpl): IDChecker
}