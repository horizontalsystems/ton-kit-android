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
package io.swagger.client.apis

import io.swagger.client.infrastructure.ApiClient
import io.swagger.client.infrastructure.ClientError
import io.swagger.client.infrastructure.ClientException
import io.swagger.client.infrastructure.RequestConfig
import io.swagger.client.infrastructure.RequestMethod
import io.swagger.client.infrastructure.ResponseType
import io.swagger.client.infrastructure.ServerError
import io.swagger.client.infrastructure.ServerException
import io.swagger.client.infrastructure.Success
import io.swagger.client.models.InlineResponse200
import io.swagger.client.models.ServiceStatus

class UtilitiesApi(basePath: kotlin.String = "https://tonapi.io") : ApiClient(basePath) {

    /**
     * 
     * parse address and display in all formats
     * @param accountId account ID 
     * @return InlineResponse200
     */
    @Suppress("UNCHECKED_CAST")
    fun addressParse(accountId: kotlin.String): InlineResponse200 {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/address/{account_id}/parse".replace("{" + "account_id" + "}", "$accountId")
        )
        val response = request<InlineResponse200>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as InlineResponse200
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Status
     * @return ServiceStatus
     */
    @Suppress("UNCHECKED_CAST")
    fun status(): ServiceStatus {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/status"
        )
        val response = request<ServiceStatus>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ServiceStatus
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
