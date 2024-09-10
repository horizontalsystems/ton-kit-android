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
 * @param amount Number of nanocoins to send. Decimal string.
 * @param payload Raw one-cell BoC encoded in hex.
 * @param stateInit Raw once-cell BoC encoded in hex.
 */


data class SignRawMessage (

    @Json(name = "address")
    val address: kotlin.String,

    /* Number of nanocoins to send. Decimal string. */
    @Json(name = "amount")
    val amount: kotlin.String,

    /* Raw one-cell BoC encoded in hex. */
    @Json(name = "payload")
    val payload: kotlin.String? = null,

    /* Raw once-cell BoC encoded in hex. */
    @Json(name = "stateInit")
    val stateInit: kotlin.String? = null

) {


}
