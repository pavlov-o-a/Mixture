package com.mixture.packagecheck.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import javax.inject.Inject


class PCViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PCViewModelImpl() as T
    }
}

interface PCViewModelProvider {
    fun getViewModel(owner: ViewModelStoreOwner): PCViewModel
}

class PCViewModelProviderImpl @Inject constructor(private val factory: PCViewModelFactory) :
    PCViewModelProvider {

    override fun getViewModel(owner: ViewModelStoreOwner): PCViewModel {
        return ViewModelProvider(owner, factory).get(PCViewModelImpl::class.java)
    }
}