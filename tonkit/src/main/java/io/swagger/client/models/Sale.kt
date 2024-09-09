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

import io.swagger.client.models.AccountAddress
import io.swagger.client.models.Price

/**
 * 
 * @param address 
 * @param market 
 * @param owner 
 * @param price 
 */
data class Sale (

    val address: kotlin.String,
    val market: AccountAddress,
    val owner: AccountAddress? = null,
    val price: Price
) {
}