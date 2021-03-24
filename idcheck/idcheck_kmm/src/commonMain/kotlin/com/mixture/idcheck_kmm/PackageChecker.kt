package com.mixture.idcheck_kmm

import com.mixture.common_kmm.entities.app.DomainResult
import com.mixture.common_kmm.entities.app.PackageAvailability

interface PackageChecker {
    suspend fun checkPackageAvailability(packageId: String): DomainResult<PackageAvailability>
}