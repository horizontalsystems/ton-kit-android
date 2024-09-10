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

import io.tonapi.models.SignRawMessage

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param relayAddress 
 * @param commission Commission for the transaction. In nanocoins.
 * @param from 
 * @param validUntil 
 * @param messages 
 */


data class SignRawParams (

    @Json(name = "relay_address")
    val relayAddress: kotlin.String,

    /* Commission for the transaction. In nanocoins. */
    @Json(name = "commission")
    val commission: kotlin.String,

    @Json(name = "from")
    val from: kotlin.String,

    @Json(name = "valid_until")
    val validUntil: kotlin.Long,

    @Json(name = "messages")
    val messages: kotlin.collections.List<SignRawMessage>

) {


}

