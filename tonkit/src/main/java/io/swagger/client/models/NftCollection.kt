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
import io.swagger.client.models.ImagePreview
import io.swagger.client.models.NftApprovedBy

/**
 * 
 * @param address 
 * @param nextItemIndex 
 * @param owner 
 * @param rawCollectionContent 
 * @param metadata 
 * @param previews 
 * @param approvedBy 
 */
data class NftCollection (

    val address: kotlin.String,
    val nextItemIndex: kotlin.Long,
    val owner: AccountAddress? = null,
    val rawCollectionContent: kotlin.String,
    val metadata: kotlin.collections.Map<kotlin.String, kotlin.Any>? = null,
    val previews: kotlin.Array<ImagePreview>? = null,
    val approvedBy: NftApprovedBy
) {
}