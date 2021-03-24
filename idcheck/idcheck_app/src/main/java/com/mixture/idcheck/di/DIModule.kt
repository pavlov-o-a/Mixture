package com.mixture.idcheck.di

import com.mixture.idcheck.domain.IDChecker
import com.mixture.idcheck.domain.IDCheckerImpl
import com.mixture.idcheck.ui.IdCheckViewModelProvider
import com.mixture.idcheck.ui.IdCheckViewModelProviderImpl
import com.mixture.idcheck_kmm.PackageChecker
import com.mixture.idcheck_kmm.PackageCheckerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DIModule {

    @Binds
    abstract fun pcViewModuleProvider(idCheckViewModelProviderImpl: IdCheckViewModelProviderImpl):
            IdCheckViewModelProvider

    @Binds
    abstract fun idChecker(checker: IDCheckerImpl): IDChecker

    companion object {
        @Provides
        @DIScope
        fun packageChecker(): PackageChecker {
            return PackageCheckerImpl()
        }

    }
}