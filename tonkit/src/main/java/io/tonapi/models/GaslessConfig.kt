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

import io.tonapi.models.GaslessConfigGasJettonsInner

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param relayAddress sending excess to this address decreases the commission of a gasless transfer
 * @param gasJettons list of jettons, any of them can be used to pay for gas
 */


data class GaslessConfig (

    /* sending excess to this address decreases the commission of a gasless transfer */
    @Json(name = "relay_address")
    val relayAddress: kotlin.String,

    /* list of jettons, any of them can be used to pay for gas */
    @Json(name = "gas_jettons")
    val gasJettons: kotlin.collections.List<GaslessConfigGasJettonsInner>

) {


}

