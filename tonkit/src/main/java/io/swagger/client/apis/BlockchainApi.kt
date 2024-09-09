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
import io.swagger.client.infrastructure.toMultiValue
import io.swagger.client.models.BlockchainAccountInspect
import io.swagger.client.models.BlockchainBlock
import io.swagger.client.models.BlockchainBlockShards
import io.swagger.client.models.BlockchainBlocks
import io.swagger.client.models.BlockchainConfig
import io.swagger.client.models.BlockchainRawAccount
import io.swagger.client.models.MethodExecutionResult
import io.swagger.client.models.RawBlockchainConfig
import io.swagger.client.models.ReducedBlocks
import io.swagger.client.models.Transaction
import io.swagger.client.models.Transactions
import io.swagger.client.models.Validators

class BlockchainApi(basePath: kotlin.String = "https://tonapi.io") : ApiClient(basePath) {

    /**
     * 
     * Blockchain account inspect
     * @param accountId account ID 
     * @return BlockchainAccountInspect
     */
    @Suppress("UNCHECKED_CAST")
    fun blockchainAccountInspect(accountId: kotlin.String): BlockchainAccountInspect {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/accounts/{account_id}/inspect".replace("{" + "account_id" + "}", "$accountId")
        )
        val response = request<BlockchainAccountInspect>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as BlockchainAccountInspect
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Execute get method for account
     * @param accountId account ID 
     * @param methodName contract get method name 
     * @param args  (optional)
     * @return MethodExecutionResult
     */
    @Suppress("UNCHECKED_CAST")
    fun execGetMethodForBlockchainAccount(accountId: kotlin.String, methodName: kotlin.String, args: kotlin.Array<kotlin.String>? = null): MethodExecutionResult {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            if (args != null) {
                put("args", toMultiValue(args.toList(), "multi"))
            }
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/accounts/{account_id}/methods/{method_name}".replace("{" + "account_id" + "}", "$accountId").replace("{" + "method_name" + "}", "$methodName"), query = localVariableQuery
        )
        val response = request<MethodExecutionResult>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as MethodExecutionResult
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get account transactions
     * @param accountId account ID 
     * @param afterLt omit this parameter to get last transactions (optional)
     * @param beforeLt omit this parameter to get last transactions (optional)
     * @param limit  (optional, default to 100)
     * @param sortOrder  (optional, default to desc)
     * @return Transactions
     */
    @Suppress("UNCHECKED_CAST")
    fun getBlockchainAccountTransactions(accountId: kotlin.String, afterLt: kotlin.Long? = null, beforeLt: kotlin.Long? = null, limit: kotlin.Int? = null, sortOrder: kotlin.String? = null): Transactions {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            if (afterLt != null) {
                put("after_lt", listOf(afterLt.toString()))
            }
            if (beforeLt != null) {
                put("before_lt", listOf(beforeLt.toString()))
            }
            if (limit != null) {
                put("limit", listOf(limit.toString()))
            }
            if (sortOrder != null) {
                put("sort_order", listOf(sortOrder.toString()))
            }
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/accounts/{account_id}/transactions".replace("{" + "account_id" + "}", "$accountId"), query = localVariableQuery
        )
        val response = request<Transactions>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Transactions
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get blockchain block data
     * @param blockId block ID 
     * @return BlockchainBlock
     */
    @Suppress("UNCHECKED_CAST")
    fun getBlockchainBlock(blockId: kotlin.String): BlockchainBlock {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/blocks/{block_id}".replace("{" + "block_id" + "}", "$blockId")
        )
        val response = request<BlockchainBlock>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as BlockchainBlock
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get transactions from block
     * @param blockId block ID 
     * @return Transactions
     */
    @Suppress("UNCHECKED_CAST")
    fun getBlockchainBlockTransactions(blockId: kotlin.String): Transactions {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/blocks/{block_id}/transactions".replace("{" + "block_id" + "}", "$blockId")
        )
        val response = request<Transactions>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Transactions
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get blockchain config
     * @return BlockchainConfig
     */
    @Suppress("UNCHECKED_CAST")
    fun getBlockchainConfig(): BlockchainConfig {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/config"
        )
        val response = request<BlockchainConfig>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as BlockchainConfig
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get blockchain config from a specific block, if present.
     * @param masterchainSeqno masterchain block seqno 
     * @return BlockchainConfig
     */
    @Suppress("UNCHECKED_CAST")
    fun getBlockchainConfigFromBlock(masterchainSeqno: kotlin.Int): BlockchainConfig {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/masterchain/{masterchain_seqno}/config".replace("{" + "masterchain_seqno" + "}", "$masterchainSeqno")
        )
        val response = request<BlockchainConfig>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as BlockchainConfig
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get all blocks in all shards and workchains between target and previous masterchain block according to shards last blocks snapshot in masterchain.  We don&#x27;t recommend to build your app around this method because it has problem with scalability and will work very slow in the future.
     * @param masterchainSeqno masterchain block seqno 
     * @return BlockchainBlocks
     */
    @Suppress("UNCHECKED_CAST")
    fun getBlockchainMasterchainBlocks(masterchainSeqno: kotlin.Int): BlockchainBlocks {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/masterchain/{masterchain_seqno}/blocks".replace("{" + "masterchain_seqno" + "}", "$masterchainSeqno")
        )
        val response = request<BlockchainBlocks>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as BlockchainBlocks
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get last known masterchain block
     * @return BlockchainBlock
     */
    @Suppress("UNCHECKED_CAST")
    fun getBlockchainMasterchainHead(): BlockchainBlock {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/masterchain-head"
        )
        val response = request<BlockchainBlock>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as BlockchainBlock
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get blockchain block shards
     * @param masterchainSeqno masterchain block seqno 
     * @return BlockchainBlockShards
     */
    @Suppress("UNCHECKED_CAST")
    fun getBlockchainMasterchainShards(masterchainSeqno: kotlin.Int): BlockchainBlockShards {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/masterchain/{masterchain_seqno}/shards".replace("{" + "masterchain_seqno" + "}", "$masterchainSeqno")
        )
        val response = request<BlockchainBlockShards>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as BlockchainBlockShards
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get all transactions in all shards and workchains between target and previous masterchain block according to shards last blocks snapshot in masterchain. We don&#x27;t recommend to build your app around this method because it has problem with scalability and will work very slow in the future.
     * @param masterchainSeqno masterchain block seqno 
     * @return Transactions
     */
    @Suppress("UNCHECKED_CAST")
    fun getBlockchainMasterchainTransactions(masterchainSeqno: kotlin.Int): Transactions {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/masterchain/{masterchain_seqno}/transactions".replace("{" + "masterchain_seqno" + "}", "$masterchainSeqno")
        )
        val response = request<Transactions>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Transactions
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get low-level information about an account taken directly from the blockchain.
     * @param accountId account ID 
     * @return BlockchainRawAccount
     */
    @Suppress("UNCHECKED_CAST")
    fun getBlockchainRawAccount(accountId: kotlin.String): BlockchainRawAccount {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/accounts/{account_id}".replace("{" + "account_id" + "}", "$accountId")
        )
        val response = request<BlockchainRawAccount>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as BlockchainRawAccount
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get transaction data
     * @param transactionId transaction ID 
     * @return Transaction
     */
    @Suppress("UNCHECKED_CAST")
    fun getBlockchainTransaction(transactionId: kotlin.String): Transaction {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/transactions/{transaction_id}".replace("{" + "transaction_id" + "}", "$transactionId")
        )
        val response = request<Transaction>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Transaction
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get transaction data by message hash
     * @param msgId message ID 
     * @return Transaction
     */
    @Suppress("UNCHECKED_CAST")
    fun getBlockchainTransactionByMessageHash(msgId: kotlin.String): Transaction {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/messages/{msg_id}/transaction".replace("{" + "msg_id" + "}", "$msgId")
        )
        val response = request<Transaction>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Transaction
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get blockchain validators
     * @return Validators
     */
    @Suppress("UNCHECKED_CAST")
    fun getBlockchainValidators(): Validators {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/validators"
        )
        val response = request<Validators>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as Validators
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get raw blockchain config
     * @return RawBlockchainConfig
     */
    @Suppress("UNCHECKED_CAST")
    fun getRawBlockchainConfig(): RawBlockchainConfig {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/config/raw"
        )
        val response = request<RawBlockchainConfig>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as RawBlockchainConfig
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get raw blockchain config from a specific block, if present.
     * @param masterchainSeqno masterchain block seqno 
     * @return RawBlockchainConfig
     */
    @Suppress("UNCHECKED_CAST")
    fun getRawBlockchainConfigFromBlock(masterchainSeqno: kotlin.Int): RawBlockchainConfig {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/masterchain/{masterchain_seqno}/config/raw".replace("{" + "masterchain_seqno" + "}", "$masterchainSeqno")
        )
        val response = request<RawBlockchainConfig>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as RawBlockchainConfig
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Get reduced blockchain blocks data
     * @param from  
     * @param to  
     * @return ReducedBlocks
     */
    @Suppress("UNCHECKED_CAST")
    fun getReducedBlockchainBlocks(from: kotlin.Long, to: kotlin.Long): ReducedBlocks {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("from", listOf(from.toString()))
            put("to", listOf(to.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/v2/blockchain/reduced/blocks", query = localVariableQuery
        )
        val response = request<ReducedBlocks>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ReducedBlocks
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * Send message to blockchain
     * @param body both a single boc and a batch of boc serialized in base64/hex are accepted 
     * @return void
     */
    fun sendBlockchainMessage(body: kotlin.Any): Unit {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/v2/blockchain/message"
        )
        val response = request<Any?>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
