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
 * @param boc 
 * @param interfaces 
 */


data class StateInit (

    @Json(name = "boc")
    val boc: kotlin.String,

    @Json(name = "interfaces")
    val interfaces: kotlin.collections.List<kotlin.String>

) {


}

