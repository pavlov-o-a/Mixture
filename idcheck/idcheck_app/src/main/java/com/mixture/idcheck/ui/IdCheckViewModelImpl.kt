package com.mixture.idcheck.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mixture.common.utils.Trigger
import com.mixture.common.utils.trigger
import com.mixture.common_kmm.entities.app.DomainError
import com.mixture.idcheck.domain.IDChecker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    private val shortId = MutableLiveData<Int>()

    companion object {
        private const val MIN_ID_LENGTH = 3
    }

    override fun navigationClicked() {
        navigateUp.trigger()
    }

    override fun textChanged() {
        wrongVisibility.value = false
        okVisibility.value = false
    }

    override fun checkClicked(packageId: String) {
        if (packageId.length > MIN_ID_LENGTH) {
            checkVisibility.value = false
            okVisibility.value = false
            wrongVisibility.value = false
            progressVisibility.value = true
            viewModelScope.launch {
                try {
                    withContext(Dispatchers.IO) {
                        idChecker.checkPackageAvailability(packageId)
                    }.process(
                        { availability ->
                            wrongVisibility.value = !availability.isAvailable
                            okVisibility.value = availability.isAvailable
                        },
                        { error ->
                            error?.let { errorData.value = it }
                        }
                    )
                } catch (exc: Exception) {
                    exc.printStackTrace()
                }
                progressVisibility.value = false
                checkVisibility.value = true
            }
        } else {
            shortId.value = MIN_ID_LENGTH
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

    override fun shortId(): LiveData<Int> = shortId
}