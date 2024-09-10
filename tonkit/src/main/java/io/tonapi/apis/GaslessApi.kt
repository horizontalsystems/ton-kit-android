/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package io.tonapi.apis

import java.io.IOException
import okhttp3.OkHttpClient
import okhttp3.HttpUrl

import io.tonapi.models.GaslessConfig
import io.tonapi.models.GaslessEstimateRequest
import io.tonapi.models.GaslessSendRequest
import io.tonapi.models.SignRawParams
import io.tonapi.models.StatusDefaultResponse

import com.squareup.moshi.Json

import io.tonapi.infrastructure.ApiClient
import io.tonapi.infrastructure.ApiResponse
import io.tonapi.infrastructure.ClientException
import io.tonapi.infrastructure.ClientError
import io.tonapi.infrastructure.ServerException
import io.tonapi.infrastructure.ServerError
import io.tonapi.infrastructure.MultiValueMap
import io.tonapi.infrastructure.PartConfig
import io.tonapi.infrastructure.RequestConfig
import io.tonapi.infrastructure.RequestMethod
import io.tonapi.infrastructure.ResponseType
import io.tonapi.infrastructure.Success
import io.tonapi.infrastructure.toMultiValue

class GaslessApi(basePath: kotlin.String = defaultBasePath, client: OkHttpClient = ApiClient.defaultClient) : ApiClient(basePath, client) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "https://tonapi.io")
        }
    }

    /**
     * 
     * Returns configuration of gasless transfers
     * @return GaslessConfig
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun gaslessConfig() : GaslessConfig {
        val localVarResponse = gaslessConfigWithHttpInfo()

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as GaslessConfig
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()} ${localVarError.body}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
     * 
     * Returns configuration of gasless transfers
     * @return ApiResponse<GaslessConfig?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun gaslessConfigWithHttpInfo() : ApiResponse<GaslessConfig?> {
        val localVariableConfig = gaslessConfigRequestConfig()

        return request<Unit, GaslessConfig>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation gaslessConfig
     *
     * @return RequestConfig
     */
    fun gaslessConfigRequestConfig() : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/v2/gasless/config",
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = false,
            body = localVariableBody
        )
    }

    /**
     * 
     * Estimates the cost of the given messages and returns a payload to sign
     * @param masterId jetton to pay commission
     * @param gaslessEstimateRequest bag-of-cells serialized to hex
     * @return SignRawParams
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun gaslessEstimate(masterId: kotlin.String, gaslessEstimateRequest: GaslessEstimateRequest) : SignRawParams {
        val localVarResponse = gaslessEstimateWithHttpInfo(masterId = masterId, gaslessEstimateRequest = gaslessEstimateRequest)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as SignRawParams
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()} ${localVarError.body}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
     * 
     * Estimates the cost of the given messages and returns a payload to sign
     * @param masterId jetton to pay commission
     * @param gaslessEstimateRequest bag-of-cells serialized to hex
     * @return ApiResponse<SignRawParams?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun gaslessEstimateWithHttpInfo(masterId: kotlin.String, gaslessEstimateRequest: GaslessEstimateRequest) : ApiResponse<SignRawParams?> {
        val localVariableConfig = gaslessEstimateRequestConfig(masterId = masterId, gaslessEstimateRequest = gaslessEstimateRequest)

        return request<GaslessEstimateRequest, SignRawParams>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation gaslessEstimate
     *
     * @param masterId jetton to pay commission
     * @param gaslessEstimateRequest bag-of-cells serialized to hex
     * @return RequestConfig
     */
    fun gaslessEstimateRequestConfig(masterId: kotlin.String, gaslessEstimateRequest: GaslessEstimateRequest) : RequestConfig<GaslessEstimateRequest> {
        val localVariableBody = gaslessEstimateRequest
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/v2/gasless/estimate/{master_id}".replace("{"+"master_id"+"}", encodeURIComponent(masterId.toString())),
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = false,
            body = localVariableBody
        )
    }

    /**
     * 
     * Submits the signed gasless transaction message to the network
     * @param gaslessSendRequest bag-of-cells serialized to hex
     * @return void
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun gaslessSend(gaslessSendRequest: GaslessSendRequest) : Unit {
        val localVarResponse = gaslessSendWithHttpInfo(gaslessSendRequest = gaslessSendRequest)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()} ${localVarError.body}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
     * 
     * Submits the signed gasless transaction message to the network
     * @param gaslessSendRequest bag-of-cells serialized to hex
     * @return ApiResponse<Unit?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Throws(IllegalStateException::class, IOException::class)
    fun gaslessSendWithHttpInfo(gaslessSendRequest: GaslessSendRequest) : ApiResponse<Unit?> {
        val localVariableConfig = gaslessSendRequestConfig(gaslessSendRequest = gaslessSendRequest)

        return request<GaslessSendRequest, Unit>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation gaslessSend
     *
     * @param gaslessSendRequest bag-of-cells serialized to hex
     * @return RequestConfig
     */
    fun gaslessSendRequestConfig(gaslessSendRequest: GaslessSendRequest) : RequestConfig<GaslessSendRequest> {
        val localVariableBody = gaslessSendRequest
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/v2/gasless/send",
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = false,
            body = localVariableBody
        )
    }


    private fun encodeURIComponent(uriComponent: kotlin.String): kotlin.String =
        HttpUrl.Builder().scheme("http").host("localhost").addPathSegment(uriComponent).build().encodedPathSegments[0]
}