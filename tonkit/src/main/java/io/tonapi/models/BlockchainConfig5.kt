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
 * @param feeBurnNom 
 * @param feeBurnDenom 
 * @param blackholeAddr 
 */


data class BlockchainConfig5 (

    @Json(name = "fee_burn_nom")
    val feeBurnNom: kotlin.Long,

    @Json(name = "fee_burn_denom")
    val feeBurnDenom: kotlin.Long,

    @Json(name = "blackhole_addr")
    val blackholeAddr: kotlin.String? = null

) {


}
