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
 * List of mandatory parameters of the blockchain config.
 *
 * @param mandatoryParams 
 */


data class BlockchainConfig9 (

    @Json(name = "mandatory_params")
    val mandatoryParams: kotlin.collections.List<kotlin.Int>

) {


}

