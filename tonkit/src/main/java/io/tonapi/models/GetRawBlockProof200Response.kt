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
import io.tonapi.models.GetRawBlockProof200ResponseStepsInner

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param complete 
 * @param from 
 * @param to 
 * @param steps 
 */


data class GetRawBlockProof200Response (

    @Json(name = "complete")
    val complete: kotlin.Boolean,

    @Json(name = "from")
    val from: BlockRaw,

    @Json(name = "to")
    val to: BlockRaw,

    @Json(name = "steps")
    val steps: kotlin.collections.List<GetRawBlockProof200ResponseStepsInner>

) {


}
