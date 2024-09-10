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
 * @param acceptNewContracts 
 * @param ratePerMbDay 
 * @param maxSpan 
 * @param minimalFileSize 
 * @param maximalFileSize 
 */


data class StorageProvider (

    @Json(name = "address")
    val address: kotlin.String,

    @Json(name = "accept_new_contracts")
    val acceptNewContracts: kotlin.Boolean,

    @Json(name = "rate_per_mb_day")
    val ratePerMbDay: kotlin.Long,

    @Json(name = "max_span")
    val maxSpan: kotlin.Long,

    @Json(name = "minimal_file_size")
    val minimalFileSize: kotlin.Long,

    @Json(name = "maximal_file_size")
    val maximalFileSize: kotlin.Long

) {


}

