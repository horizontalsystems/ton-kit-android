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
 * @param secpPubkey 
 */


data class Oracle (

    @Json(name = "address")
    val address: kotlin.String,

    @Json(name = "secp_pubkey")
    val secpPubkey: kotlin.String

) {


}

