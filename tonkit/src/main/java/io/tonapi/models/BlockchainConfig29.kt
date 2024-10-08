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
 * The configuration for the consensus protocol above catchain.
 *
 * @param roundCandidates 
 * @param nextCandidateDelayMs 
 * @param consensusTimeoutMs 
 * @param fastAttempts 
 * @param attemptDuration 
 * @param catchainMaxDeps 
 * @param maxBlockBytes 
 * @param maxCollatedBytes 
 * @param flags 
 * @param newCatchainIds 
 * @param protoVersion 
 * @param catchainMaxBlocksCoeff 
 */


data class BlockchainConfig29 (

    @Json(name = "round_candidates")
    val roundCandidates: kotlin.Long,

    @Json(name = "next_candidate_delay_ms")
    val nextCandidateDelayMs: kotlin.Long,

    @Json(name = "consensus_timeout_ms")
    val consensusTimeoutMs: kotlin.Long,

    @Json(name = "fast_attempts")
    val fastAttempts: kotlin.Long,

    @Json(name = "attempt_duration")
    val attemptDuration: kotlin.Long,

    @Json(name = "catchain_max_deps")
    val catchainMaxDeps: kotlin.Long,

    @Json(name = "max_block_bytes")
    val maxBlockBytes: kotlin.Long,

    @Json(name = "max_collated_bytes")
    val maxCollatedBytes: kotlin.Long,

    @Json(name = "flags")
    val flags: kotlin.Int? = null,

    @Json(name = "new_catchain_ids")
    val newCatchainIds: kotlin.Boolean? = null,

    @Json(name = "proto_version")
    val protoVersion: kotlin.Long? = null,

    @Json(name = "catchain_max_blocks_coeff")
    val catchainMaxBlocksCoeff: kotlin.Long? = null

) {


}

