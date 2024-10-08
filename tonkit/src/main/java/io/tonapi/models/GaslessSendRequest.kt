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
 * @param walletPublicKey hex encoded public key
 * @param boc 
 */


data class GaslessSendRequest (

    /* hex encoded public key */
    @Json(name = "wallet_public_key")
    val walletPublicKey: kotlin.String,

    @Json(name = "boc")
    val boc: kotlin.String

) {


}

