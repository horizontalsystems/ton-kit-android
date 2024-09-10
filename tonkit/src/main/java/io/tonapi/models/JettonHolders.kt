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

import io.tonapi.models.JettonHoldersAddressesInner

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param addresses 
 * @param total total number of holders
 */


data class JettonHolders (

    @Json(name = "addresses")
    val addresses: kotlin.collections.List<JettonHoldersAddressesInner>,

    /* total number of holders */
    @Json(name = "total")
    val total: kotlin.Long

) {


}
