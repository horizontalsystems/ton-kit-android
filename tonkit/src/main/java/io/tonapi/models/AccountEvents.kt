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

import io.tonapi.models.AccountEvent

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param events 
 * @param nextFrom 
 */


data class AccountEvents (

    @Json(name = "events")
    val events: kotlin.collections.List<AccountEvent>,

    @Json(name = "next_from")
    val nextFrom: kotlin.Long

) {


}
