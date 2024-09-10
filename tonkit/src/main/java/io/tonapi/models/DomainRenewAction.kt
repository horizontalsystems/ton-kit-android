/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package io.tonapi.models

import io.tonapi.models.AccountAddress

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param domain 
 * @param contractAddress 
 * @param renewer 
 */


data class DomainRenewAction (

    @Json(name = "domain")
    val domain: kotlin.String,

    @Json(name = "contract_address")
    val contractAddress: kotlin.String,

    @Json(name = "renewer")
    val renewer: AccountAddress

) {


}

