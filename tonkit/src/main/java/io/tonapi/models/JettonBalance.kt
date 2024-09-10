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
import io.tonapi.models.JettonBalanceLock
import io.tonapi.models.JettonPreview
import io.tonapi.models.TokenRates

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param balance 
 * @param walletAddress 
 * @param jetton 
 * @param price 
 * @param extensions 
 * @param lock 
 */


data class JettonBalance (

    @Json(name = "balance")
    val balance: kotlin.String,

    @Json(name = "wallet_address")
    val walletAddress: AccountAddress,

    @Json(name = "jetton")
    val jetton: JettonPreview,

    @Json(name = "price")
    val price: TokenRates? = null,

    @Json(name = "extensions")
    val extensions: kotlin.collections.List<kotlin.String>? = null,

    @Json(name = "lock")
    val lock: JettonBalanceLock? = null

) {


}

