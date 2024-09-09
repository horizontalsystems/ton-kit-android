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
import io.swagger.client.infrastructure.MultiValueMap
import io.swagger.client.infrastructure.RequestConfig
import io.swagger.client.infrastructure.RequestMethod
import io.swagger.client.infrastructure.ResponseType
import io.swagger.client.infrastructure.ServerError
import io.swagger.client.infrastructure.ServerException
import io.swagger.client.infrastructure.Success
import io.swagger.client.models.AccountEvent
import io.swagger.client.models.DecodedMessage
import io.swagger.client.models.Event
import io.swagger.client.models.MessageConsequences
import io.swagger.client.models.Trace

class EmulationApi(basePath: kotlin.String = "https://tonapi.io") : ApiClient(basePath) {

    /**
     * 
     * Decode a given message. Only external incoming messages can be decoded currently.
     * @param body bag-of-cells serialized to hex 
     * @return DecodedMessage
     */
    @Suppress("UNCHECKED_CAST")
    fun decodeMessage(body: kotlin.Any): DecodedMessage {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v2/message/decode"
        )
        val response = request<DecodedMessage>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as DecodedMessage
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Emulate sending message to blockchain
     * @param body bag-of-cells serialized to hex 
     * @param accountId account ID 
     * @param acceptLanguage  (optional, default to en)
     * @param ignoreSignatureCheck  (optional)
     * @return AccountEvent
     */
    @Suppress("UNCHECKED_CAST")
    fun emulateMessageToAccountEvent(body: kotlin.Any, accountId: kotlin.String, acceptLanguage: kotlin.String? = null, ignoreSignatureCheck: kotlin.Boolean? = null): AccountEvent {
        val localVariableBody: kotlin.Any? = body
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            if (ignoreSignatureCheck != null) {
                put("ignore_signature_check", listOf(ignoreSignatureCheck.toString()))
            }
        }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        acceptLanguage?.apply {
            localVariableHeaders["Accept-Language"] = this.toString()
        }
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "application/json"
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v2/accounts/{account_id}/events/emulate".replace("{" + "account_id" + "}", "$accountId"), query = localVariableQuery, headers = localVariableHeaders
        )
        val response = request<AccountEvent>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AccountEvent
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Emulate sending message to blockchain
     * @param body bag-of-cells serialized to hex 
     * @param acceptLanguage  (optional, default to en)
     * @param ignoreSignatureCheck  (optional)
     * @return Event
     */
    @Suppress("UNCHECKED_CAST")
    fun emulateMessageToEvent(body: kotlin.Any, acceptLanguage: kotlin.String? = null, ignoreSignatureCheck: kotlin.Boolean? = null): Event {
        val localVariableBody: kotlin.Any? = body
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            if (ignoreSignatureCheck != null) {
                put("ignore_signature_check", listOf(ignoreSignatureCheck.toString()))
            }
        }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        acceptLanguage?.apply {
            localVariableHeaders["Accept-Language"] = this.toString()
        }
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "application/json"
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v2/events/emulate", query = localVariableQuery, headers = localVariableHeaders
        )
        val response = request<Event>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Event
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Emulate sending message to blockchain
     * @param body bag-of-cells serialized to hex 
     * @param ignoreSignatureCheck  (optional)
     * @return Trace
     */
    @Suppress("UNCHECKED_CAST")
    fun emulateMessageToTrace(body: kotlin.Any, ignoreSignatureCheck: kotlin.Boolean? = null): Trace {
        val localVariableBody: kotlin.Any? = body
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            if (ignoreSignatureCheck != null) {
                put("ignore_signature_check", listOf(ignoreSignatureCheck.toString()))
            }
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v2/traces/emulate", query = localVariableQuery
        )
        val response = request<Trace>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Trace
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Emulate sending message to blockchain
     * @param body bag-of-cells serialized to base64/hex and additional parameters to configure emulation 
     * @param acceptLanguage  (optional, default to en)
     * @return MessageConsequences
     */
    @Suppress("UNCHECKED_CAST")
    fun emulateMessageToWallet(body: kotlin.Any, acceptLanguage: kotlin.String? = null): MessageConsequences {
        val localVariableBody: kotlin.Any? = body
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        acceptLanguage?.apply {
            localVariableHeaders["Accept-Language"] = this.toString()
        }
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "application/json"
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v2/wallet/emulate", headers = localVariableHeaders
        )
        val response = request<MessageConsequences>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as MessageConsequences
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
