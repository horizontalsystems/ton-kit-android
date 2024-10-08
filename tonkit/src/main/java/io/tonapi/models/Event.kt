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

import io.tonapi.models.Action
import io.tonapi.models.ValueFlow

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param eventId 
 * @param timestamp 
 * @param actions 
 * @param valueFlow 
 * @param isScam scam
 * @param lt 
 * @param inProgress Event is not finished yet. Transactions still happening
 */


data class Event (

    @Json(name = "event_id")
    val eventId: kotlin.String,

    @Json(name = "timestamp")
    val timestamp: kotlin.Long,

    @Json(name = "actions")
    val actions: kotlin.collections.List<Action>,

    @Json(name = "value_flow")
    val valueFlow: kotlin.collections.List<ValueFlow>,

    /* scam */
    @Json(name = "is_scam")
    val isScam: kotlin.Boolean,

    @Json(name = "lt")
    val lt: kotlin.Long,

    /* Event is not finished yet. Transactions still happening */
    @Json(name = "in_progress")
    val inProgress: kotlin.Boolean

) {


}

