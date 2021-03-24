package com.mixture.idcheck.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.mixture.idcheck.di.DIScope
import javax.inject.Inject


class IdCheckViewModelFactory @Inject constructor(private val viewModel: IdCheckViewModelImpl) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel as T
    }
}

interface IdCheckViewModelProvider {
    fun getViewModel(owner: ViewModelStoreOwner): IdCheckViewModel
}

@DIScope
class IdCheckViewModelProviderImpl @Inject constructor(private val factory: IdCheckViewModelFactory) :
    IdCheckViewModelProvider {

    override fun getViewModel(owner: ViewModelStoreOwner): IdCheckViewModel {
        return ViewModelProvider(owner, factory).get(IdCheckViewModelImpl::class.java)
    }
}