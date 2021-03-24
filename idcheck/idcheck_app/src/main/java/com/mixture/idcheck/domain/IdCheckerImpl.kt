package com.mixture.idcheck.domain

import com.mixture.common_kmm.entities.app.DomainError
import com.mixture.common_kmm.entities.app.DomainResult
import com.mixture.common_kmm.entities.app.PackageAvailability
import com.mixture.idcheck.di.DIScope
import com.mixture.idcheck_kmm.PackageChecker
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

@DIScope
class IDCheckerImpl @Inject constructor(idChecker: PackageChecker) : IDChecker {

    override suspend fun checkPackageAvailability(): DomainResult<PackageAvailability> {
        delay(1000)
        return if (Random.nextBoolean())
            DomainResult(PackageAvailability(Random.nextBoolean()))
        else
            DomainResult(null, DomainError.NETWORK_ERROR)
    }
}