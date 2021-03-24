package com.mixture.idcheck_kmm

import com.mixture.common_kmm.entities.app.DomainResult
import com.mixture.common_kmm.entities.app.PackageAvailability
import com.mixture.common_kmm.entities.app.UnknownError
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class PackageCheckerImpl : PackageChecker {
    override suspend fun checkPackageAvailability(packageId: String): DomainResult<PackageAvailability> {
        return try {
            val client = HttpClient(CIO)
            val res: HttpResponse = client.get(
                "https://play.google.com/store/apps/details?id=$packageId"
            )
            client.close()
            DomainResult(PackageAvailability(false))
        } catch (exc: Exception) {
            if (exc is ClientRequestException) {
                if (exc.response.status == HttpStatusCode.NotFound) {
                    DomainResult(PackageAvailability(true))
                } else {
                    DomainResult(null, UnknownError(exc.message ?: "" + exc.response.status))
                }
            } else {
                DomainResult(null, UnknownError(exc.message ?: "Network error"))
            }
        }
    }
}