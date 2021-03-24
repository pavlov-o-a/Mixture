package com.mixture.idcheck.ui

import androidx.lifecycle.LiveData
import com.mixture.common.entities.app.DomainError
import com.mixture.common.utils.Trigger

interface IdCheckViewModel {

    fun navigationClicked()
    fun textChanged()
    fun checkClicked()
    fun infoClicked()
    fun navigateUpLiveData(): LiveData<Trigger>
    fun showInfoLiveData(): LiveData<Trigger>
    fun checkVisibility(): LiveData<Boolean>
    fun progressVisibility(): LiveData<Boolean>
    fun okVisibility(): LiveData<Boolean>
    fun wrongVisibility(): LiveData<Boolean>
    fun showError(): LiveData<DomainError>
}