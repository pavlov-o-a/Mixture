package com.mixture.packagecheck.domain

import com.mixture.common.entities.app.DomainResult
import com.mixture.common.entities.app.PackageAvailability

interface IDChecker {

    suspend fun checkPackageAvailability(): DomainResult<PackageAvailability>
}