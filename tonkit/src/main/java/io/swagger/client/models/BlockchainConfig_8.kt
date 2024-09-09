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
 * The network version and additional capabilities supported by the validators.
 * @param version 
 * @param capabilities 
 */
data class BlockchainConfig8 (

    val version: kotlin.Long,
    val capabilities: kotlin.Long
) {
}