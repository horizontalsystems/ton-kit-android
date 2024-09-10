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
import io.tonapi.models.InitStateRaw

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param last 
 * @param stateRootHash 
 * @param `init` 
 */


data class GetRawMasterchainInfo200Response (

    @Json(name = "last")
    val last: BlockRaw,

    @Json(name = "state_root_hash")
    val stateRootHash: kotlin.String,

    @Json(name = "init")
    val `init`: InitStateRaw

) {


}

