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

import io.tonapi.models.Transaction

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param transaction 
 * @param interfaces 
 * @param children 
 * @param emulated 
 */


data class Trace (

    @Json(name = "transaction")
    val transaction: Transaction,

    @Json(name = "interfaces")
    val interfaces: kotlin.collections.List<kotlin.String>,

    @Json(name = "children")
    val children: kotlin.collections.List<Trace>? = null,

    @Json(name = "emulated")
    val emulated: kotlin.Boolean? = null

) {


}
