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

import io.tonapi.models.MsgForwardPrices

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The cost of sending messages in the masterchain of the TON blockchain.
 *
 * @param msgForwardPrices 
 */


data class BlockchainConfig24 (

    @Json(name = "msg_forward_prices")
    val msgForwardPrices: MsgForwardPrices

) {


}

