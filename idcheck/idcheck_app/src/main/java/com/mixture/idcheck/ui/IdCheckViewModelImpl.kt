package com.mixture.idcheck.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mixture.common.entities.app.DomainError
import com.mixture.common.utils.Trigger
import com.mixture.common.utils.trigger
import com.mixture.idcheck.domain.IDChecker
import kotlinx.coroutines.launch
import javax.inject.Inject

class IdCheckViewModelImpl @Inject constructor(private val idChecker: IDChecker) : ViewModel(),
    IdCheckViewModel {
    private val navigateUp = MutableLiveData<Trigger>()
    private val showInfo = MutableLiveData<Trigger>()
    private val checkVisibility = MutableLiveData<Boolean>()
    private val progressVisibility = MutableLiveData<Boolean>()
    private val okVisibility = MutableLiveData<Boolean>()
    private val wrongVisibility = MutableLiveData<Boolean>()
    private val errorData = MutableLiveData<DomainError>()

    override fun navigationClicked() {
        navigateUp.trigger()
    }

    override fun textChanged() {
        wrongVisibility.value = false
        okVisibility.value = false
    }

    override fun checkClicked() {
        checkVisibility.value = false
        okVisibility.value = false
        wrongVisibility.value = false
        progressVisibility.value = true
        viewModelScope.launch {
            idChecker.checkPackageAvailability().process(
                { availability ->
                    wrongVisibility.value = availability.isAvailable
                    okVisibility.value = !availability.isAvailable
                },
                { error ->
                    error?.let { errorData.value = it }
                }
            )
            progressVisibility.value = false
            checkVisibility.value = true
        }
    }

    override fun infoClicked() {
        showInfo.trigger()
    }

    override fun navigateUpLiveData(): LiveData<Trigger> = navigateUp

    override fun showInfoLiveData(): LiveData<Trigger> = showInfo

    override fun checkVisibility(): LiveData<Boolean> = checkVisibility

    override fun progressVisibility(): LiveData<Boolean> = progressVisibility

    override fun okVisibility(): LiveData<Boolean> = okVisibility

    override fun wrongVisibility(): LiveData<Boolean> = wrongVisibility

    override fun showError(): LiveData<DomainError> = errorData
}