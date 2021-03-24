package com.mixture.idcheck.ui

import androidx.lifecycle.LiveData
import com.mixture.common.utils.Trigger
import com.mixture.common_kmm.entities.app.DomainError

interface IdCheckViewModel {

    fun navigationClicked()
    fun textChanged()
    fun checkClicked(packageId: String)
    fun infoClicked()
    fun navigateUpLiveData(): LiveData<Trigger>
    fun showInfoLiveData(): LiveData<Trigger>
    fun checkVisibility(): LiveData<Boolean>
    fun progressVisibility(): LiveData<Boolean>
    fun okVisibility(): LiveData<Boolean>
    fun wrongVisibility(): LiveData<Boolean>
    fun showError(): LiveData<DomainError>
    fun shortId(): LiveData<Int>
}