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

import io.tonapi.models.BlockRaw

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param ids 
 * @param transactions 
 */


data class GetRawTransactions200Response (

    @Json(name = "ids")
    val ids: kotlin.collections.List<BlockRaw>,

    @Json(name = "transactions")
    val transactions: kotlin.String

) {


}

