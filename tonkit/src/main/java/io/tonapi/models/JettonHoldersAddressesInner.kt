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
 * @param address 
 * @param owner 
 * @param balance balance in the smallest jetton's units
 */


data class JettonHoldersAddressesInner (

    @Json(name = "address")
    val address: kotlin.String,

    @Json(name = "owner")
    val owner: AccountAddress,

    /* balance in the smallest jetton's units */
    @Json(name = "balance")
    val balance: kotlin.String

) {


}

