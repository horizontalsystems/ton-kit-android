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
import io.swagger.client.models.EncryptedComment
import io.swagger.client.models.Refund

/**
 * 
 * @param sender 
 * @param recipient 
 * @param nft 
 * @param comment 
 * @param encryptedComment 
 * @param payload raw hex encoded payload
 * @param refund 
 */
data class NftItemTransferAction (

    val sender: AccountAddress? = null,
    val recipient: AccountAddress? = null,
    val nft: kotlin.String,
    val comment: kotlin.String? = null,
    val encryptedComment: EncryptedComment? = null,
    /* raw hex encoded payload */
    val payload: kotlin.String? = null,
    val refund: Refund? = null
) {
}