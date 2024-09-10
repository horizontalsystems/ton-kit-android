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
import io.tonapi.models.Refund

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param executor 
 * @param `contract` 
 * @param tonAttached amount in nanotons
 * @param operation 
 * @param payload 
 * @param refund 
 */


data class SmartContractAction (

    @Json(name = "executor")
    val executor: AccountAddress,

    @Json(name = "contract")
    val `contract`: AccountAddress,

    /* amount in nanotons */
    @Json(name = "ton_attached")
    val tonAttached: kotlin.Long,

    @Json(name = "operation")
    val operation: kotlin.String,

    @Json(name = "payload")
    val payload: kotlin.String? = null,

    @Json(name = "refund")
    val refund: Refund? = null

) {


}

