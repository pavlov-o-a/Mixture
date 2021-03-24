package com.mixture.idcheck.domain

import com.mixture.common_kmm.entities.app.DomainResult
import com.mixture.common_kmm.entities.app.PackageAvailability

interface IDChecker {

    suspend fun checkPackageAvailability(packageId: String): DomainResult<PackageAvailability>
}