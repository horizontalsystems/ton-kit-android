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
import io.tonapi.models.DecodedMessageExtInMsgDecoded

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param destination 
 * @param destinationWalletVersion 
 * @param extInMsgDecoded 
 */


data class DecodedMessage (

    @Json(name = "destination")
    val destination: AccountAddress,

    @Json(name = "destination_wallet_version")
    val destinationWalletVersion: kotlin.String,

    @Json(name = "ext_in_msg_decoded")
    val extInMsgDecoded: DecodedMessageExtInMsgDecoded? = null

) {


}

