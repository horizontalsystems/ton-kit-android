/**
 * REST api to TON blockchain explorer
 * Provide access to indexed TON blockchain
 *
 * OpenAPI spec version: 2.0.0
 * Contact: support@tonkeeper.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package io.swagger.client.models


/**
 * The stake parameters configuration in the TON blockchain.
 * @param minStake 
 * @param maxStake 
 * @param minTotalStake 
 * @param maxStakeFactor 
 */
data class BlockchainConfig17 (

    val minStake: kotlin.String,
    val maxStake: kotlin.String,
    val minTotalStake: kotlin.String,
    val maxStakeFactor: kotlin.Long
) {
}