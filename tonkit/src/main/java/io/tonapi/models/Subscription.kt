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


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param address 
 * @param walletAddress 
 * @param beneficiaryAddress 
 * @param amount 
 * @param period 
 * @param startTime 
 * @param timeout 
 * @param lastPaymentTime 
 * @param lastRequestTime 
 * @param subscriptionId 
 * @param failedAttempts 
 */


data class Subscription (

    @Json(name = "address")
    val address: kotlin.String,

    @Json(name = "wallet_address")
    val walletAddress: kotlin.String,

    @Json(name = "beneficiary_address")
    val beneficiaryAddress: kotlin.String,

    @Json(name = "amount")
    val amount: kotlin.Long,

    @Json(name = "period")
    val period: kotlin.Long,

    @Json(name = "start_time")
    val startTime: kotlin.Long,

    @Json(name = "timeout")
    val timeout: kotlin.Long,

    @Json(name = "last_payment_time")
    val lastPaymentTime: kotlin.Long,

    @Json(name = "last_request_time")
    val lastRequestTime: kotlin.Long,

    @Json(name = "subscription_id")
    val subscriptionId: kotlin.Long,

    @Json(name = "failed_attempts")
    val failedAttempts: kotlin.Int

) {


}

