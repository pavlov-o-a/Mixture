package com.mixture.packagecheck.ui

import androidx.lifecycle.LiveData
import com.mixture.common.utils.Trigger

interface PCViewModel {

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
}