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
 * @param id 
 * @param proof 
 * @param `data` 
 */


data class GetAllRawShardsInfo200Response (

    @Json(name = "id")
    val id: BlockRaw,

    @Json(name = "proof")
    val proof: kotlin.String,

    @Json(name = "data")
    val `data`: kotlin.String

) {


}

