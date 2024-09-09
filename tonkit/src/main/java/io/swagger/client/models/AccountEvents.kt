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
 * 
 * @param events 
 * @param nextFrom 
 */
data class AccountEvents (

    val events: kotlin.Array<AccountEvent>,
    val nextFrom: kotlin.Long
) {
}