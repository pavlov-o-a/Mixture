package com.mixture.packagecheck.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.mixture.packagecheck.di.DIScope
import javax.inject.Inject


class PCViewModelFactory @Inject constructor(private val viewModel: PCViewModelImpl) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel as T
    }
}

interface PCViewModelProvider {
    fun getViewModel(owner: ViewModelStoreOwner): PCViewModel
}

@DIScope
class PCViewModelProviderImpl @Inject constructor(private val factory: PCViewModelFactory) :
    PCViewModelProvider {

    override fun getViewModel(owner: ViewModelStoreOwner): PCViewModel {
        return ViewModelProvider(owner, factory).get(PCViewModelImpl::class.java)
    }
}