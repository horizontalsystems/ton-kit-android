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

import io.tonapi.models.AccountStatus

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param address 
 * @param balance 
 * @param lastActivity unix timestamp
 * @param status 
 * @param getMethods 
 * @param isWallet 
 * @param currenciesBalance {'USD': 1, 'IDR': 1000}
 * @param interfaces 
 * @param name 
 * @param isScam 
 * @param icon 
 * @param memoRequired 
 * @param isSuspended 
 */


data class Account (

    @Json(name = "address")
    val address: kotlin.String,

    @Json(name = "balance")
    val balance: kotlin.Long,

    /* unix timestamp */
    @Json(name = "last_activity")
    val lastActivity: kotlin.Long,

    @Json(name = "status")
    val status: AccountStatus,

    @Json(name = "get_methods")
    val getMethods: kotlin.collections.List<kotlin.String>,

    @Json(name = "is_wallet")
    val isWallet: kotlin.Boolean,

    /* {'USD': 1, 'IDR': 1000} */
    @Json(name = "currencies_balance")
    val currenciesBalance: kotlin.collections.Map<kotlin.String, kotlin.Any>? = null,

    @Json(name = "interfaces")
    val interfaces: kotlin.collections.List<kotlin.String>? = null,

    @Json(name = "name")
    val name: kotlin.String? = null,

    @Json(name = "is_scam")
    val isScam: kotlin.Boolean? = null,

    @Json(name = "icon")
    val icon: kotlin.String? = null,

    @Json(name = "memo_required")
    val memoRequired: kotlin.Boolean? = null,

    @Json(name = "is_suspended")
    val isSuspended: kotlin.Boolean? = null

) {


}
