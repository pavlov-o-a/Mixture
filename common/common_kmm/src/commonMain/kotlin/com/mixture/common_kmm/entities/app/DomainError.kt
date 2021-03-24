package com.mixture.common_kmm.entities.app

sealed class DomainError(val errorMessage: String = "")
class NetworkError(errorMessage: String = "") : DomainError(errorMessage)
class UnknownError(errorMessage: String = "") : DomainError(errorMessage)