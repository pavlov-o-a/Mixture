package com.mixture.idcheck.domain

import com.mixture.common.entities.app.DomainError
import com.mixture.common.entities.app.DomainResult
import com.mixture.common.entities.app.PackageAvailability
import com.mixture.idcheck.di.DIScope
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

@DIScope
class IDCheckerImpl @Inject constructor() : IDChecker {

    override suspend fun checkPackageAvailability(): DomainResult<PackageAvailability> {
        delay(1000)
        return if (Random.nextBoolean())
            DomainResult(PackageAvailability(Random.nextBoolean()))
        else
            DomainResult(null, DomainError.NETWORK_ERROR)
    }
}