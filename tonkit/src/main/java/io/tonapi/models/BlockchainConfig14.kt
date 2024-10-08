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
 * The reward in nanoTons for block creation in the TON blockchain.
 *
 * @param masterchainBlockFee 
 * @param basechainBlockFee 
 */


data class BlockchainConfig14 (

    @Json(name = "masterchain_block_fee")
    val masterchainBlockFee: kotlin.Long,

    @Json(name = "basechain_block_fee")
    val basechainBlockFee: kotlin.Long

) {


}

