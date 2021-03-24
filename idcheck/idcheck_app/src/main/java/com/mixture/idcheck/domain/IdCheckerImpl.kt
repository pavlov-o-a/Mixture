package com.mixture.idcheck.domain

import com.mixture.common_kmm.entities.app.DomainResult
import com.mixture.common_kmm.entities.app.PackageAvailability
import com.mixture.idcheck.di.DIScope
import com.mixture.idcheck_kmm.PackageChecker
import javax.inject.Inject

@DIScope
class IDCheckerImpl @Inject constructor(val idChecker: PackageChecker) : IDChecker {

    override suspend fun checkPackageAvailability(packageId: String): DomainResult<PackageAvailability> {
        return idChecker.checkPackageAvailability(packageId)
    }
}