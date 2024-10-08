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
 * @param rootHash 
 * @param fileHash 
 * @param `data` 
 */


data class GetRawBlockchainBlockState200Response (

    @Json(name = "id")
    val id: BlockRaw,

    @Json(name = "root_hash")
    val rootHash: kotlin.String,

    @Json(name = "file_hash")
    val fileHash: kotlin.String,

    @Json(name = "data")
    val `data`: kotlin.String

) {


}

