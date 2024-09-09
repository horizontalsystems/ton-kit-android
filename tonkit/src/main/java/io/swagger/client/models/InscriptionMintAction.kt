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

/**
 * 
 * @param recipient 
 * @param amount amount in minimal particles
 * @param type 
 * @param ticker 
 * @param decimals 
 */
data class InscriptionMintAction (

    val recipient: AccountAddress,
    /* amount in minimal particles */
    val amount: kotlin.String,
    val type: Type,
    val ticker: kotlin.String,
    val decimals: kotlin.Int
) {
    /**
    * 
    * Values: TON20,GRAM20
    */
    enum class Type(val value: kotlin.String){
        TON20("ton20"),
        GRAM20("gram20");
    }
}