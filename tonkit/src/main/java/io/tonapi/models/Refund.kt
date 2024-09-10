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
 * @param type 
 * @param origin 
 */


data class Refund (

    @Json(name = "type")
    val type: Refund.Type,

    @Json(name = "origin")
    val origin: kotlin.String

) {

    /**
     * 
     *
     * Values: DNSPeriodTon,DNSPeriodTg,GetGems
     */
    @JsonClass(generateAdapter = false)
    enum class Type(val value: kotlin.String) {
        @Json(name = "DNS.ton") DNSPeriodTon("DNS.ton"),
        @Json(name = "DNS.tg") DNSPeriodTg("DNS.tg"),
        @Json(name = "GetGems") GetGems("GetGems");
    }

}

