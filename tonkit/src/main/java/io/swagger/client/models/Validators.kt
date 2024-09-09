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

import io.swagger.client.models.Validator

/**
 * 
 * @param electAt 
 * @param electClose 
 * @param minStake 
 * @param totalStake 
 * @param validators 
 */
data class Validators (

    val electAt: kotlin.Long,
    val electClose: kotlin.Long,
    val minStake: kotlin.Long,
    val totalStake: kotlin.Long,
    val validators: kotlin.Array<Validator>
) {
}