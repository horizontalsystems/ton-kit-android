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
 * Values: unchanged,frozen,deleted
 */

@JsonClass(generateAdapter = false)
enum class AccStatusChange(val value: kotlin.String) {

    @Json(name = "acst_unchanged")
    unchanged("acst_unchanged"),

    @Json(name = "acst_frozen")
    frozen("acst_frozen"),

    @Json(name = "acst_deleted")
    deleted("acst_deleted"),

    @Json(name = "unknown_default_open_api")
    unknown_default_open_api("unknown_default_open_api");

    /**
     * Override [toString()] to avoid using the enum variable name as the value, and instead use
     * the actual value defined in the API spec file.
     *
     * This solves a problem when the variable name and its value are different, and ensures that
     * the client sends the correct enum values to the server always.
     */
    override fun toString(): kotlin.String = value

    companion object {
        /**
         * Converts the provided [data] to a [String] on success, null otherwise.
         */
        fun encode(data: kotlin.Any?): kotlin.String? = if (data is AccStatusChange) "$data" else null

        /**
         * Returns a valid [AccStatusChange] for [data], null otherwise.
         */
        fun decode(data: kotlin.Any?): AccStatusChange? = data?.let {
          val normalizedData = "$it".lowercase()
          values().firstOrNull { value ->
            it == value || normalizedData == "$value".lowercase()
          }
        }
    }
}

