package com.mixture.idcheck_kmm

import com.mixture.common_kmm.entities.app.DomainError
import com.mixture.common_kmm.entities.app.DomainResult
import com.mixture.common_kmm.entities.app.PackageAvailability
import kotlinx.coroutines.delay
import kotlin.random.Random

class PackageCheckerImpl : PackageChecker {
    override suspend fun checkPackageAvailability(): DomainResult<PackageAvailability> {
        delay(1000)
        return if (Random.nextBoolean())
            DomainResult(PackageAvailability(Random.nextBoolean()))
        else
            DomainResult(null, DomainError.NETWORK_ERROR)
    }
}