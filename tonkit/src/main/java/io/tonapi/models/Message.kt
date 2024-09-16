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
 * @param msgType 
 * @param createdLt 
 * @param ihrDisabled 
 * @param bounce 
 * @param bounced 
 * @param `value` 
 * @param fwdFee 
 * @param ihrFee 
 * @param importFee 
 * @param createdAt 
 * @param hash 
 * @param destination 
 * @param source 
 * @param opCode 
 * @param `init` 
 * @param rawBody hex-encoded BoC with raw message body
 * @param decodedOpName 
 * @param decodedBody 
 */


data class Message (

    @Json(name = "msg_type")
    val msgType: Message.MsgType,

    @Json(name = "created_lt")
    val createdLt: kotlin.Long,

    @Json(name = "ihr_disabled")
    val ihrDisabled: kotlin.Boolean,

    @Json(name = "bounce")
    val bounce: kotlin.Boolean,

    @Json(name = "bounced")
    val bounced: kotlin.Boolean,

    @Json(name = "value")
    val `value`: kotlin.Long,

    @Json(name = "fwd_fee")
    val fwdFee: kotlin.Long,

    @Json(name = "ihr_fee")
    val ihrFee: kotlin.Long,

    @Json(name = "import_fee")
    val importFee: kotlin.Long,

    @Json(name = "created_at")
    val createdAt: kotlin.Long,

    @Json(name = "hash")
    val hash: kotlin.String,

    @Json(name = "destination")
    val destination: AccountAddress? = null,

    @Json(name = "source")
    val source: AccountAddress? = null,

    @Json(name = "op_code")
    val opCode: kotlin.String? = null,

    @Json(name = "init")
    val `init`: StateInit? = null,

    /* hex-encoded BoC with raw message body */
    @Json(name = "raw_body")
    val rawBody: kotlin.String? = null,

    @Json(name = "decoded_op_name")
    val decodedOpName: kotlin.String? = null,

    @Json(name = "decoded_body")
    val decodedBody: kotlin.Any? = null

) {

    /**
     * 
     *
     * Values: int_msg,ext_in_msg,ext_out_msg
     */
    @JsonClass(generateAdapter = false)
    enum class MsgType(val value: kotlin.String) {
        @Json(name = "int_msg") int_msg("int_msg"),
        @Json(name = "ext_in_msg") ext_in_msg("ext_in_msg"),
        @Json(name = "ext_out_msg") ext_out_msg("ext_out_msg"),
        @Json(name = "unknown_default_open_api") unknown_default_open_api("unknown_default_open_api");
    }

}

