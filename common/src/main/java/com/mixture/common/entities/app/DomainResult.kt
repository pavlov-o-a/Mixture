package com.mixture.common.entities.app

class DomainResult<T>(val value: T?, val error: DomainError? = null) {

    fun process(onSuccess: (T) -> Unit, onError: (DomainError?) -> Unit) {
        if (value != null && error == null)
            onSuccess(value)
        else onError(error)
    }
}