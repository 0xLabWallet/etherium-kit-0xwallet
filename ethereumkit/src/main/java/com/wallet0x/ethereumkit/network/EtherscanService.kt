package com.wallet0x.ethereumkit.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.wallet0x.ethereumkit.api.models.EtherscanResponse
import com.wallet0x.ethereumkit.core.retryWhenError
import com.wallet0x.ethereumkit.core.toHexString
import com.wallet0x.ethereumkit.models.Address
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.logging.Logger

class EtherscanService(
    baseUrl: String,
    private val apiKey: String,
    private val chainId: Int  // 🆕 chainId для определения версии API
) {

    private val logger = Logger.getLogger("EtherscanService")

    private val service: EtherscanServiceAPI

    private val gson: Gson
    
    // Определяем использовать ли API V2 (только для Ethereum и BSC)
    private val useApiV2 = chainId > 0

    init {
        logger.info("EtherscanService initialized: baseUrl=$baseUrl, chainId=$chainId, useApiV2=$useApiV2")
        
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                logger.info(message)
            }
        }).setLevel(HttpLoggingInterceptor.Level.BASIC)

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(Interceptor { chain ->
                val originalRequest: Request = chain.request()
                val requestWithUserAgent: Request = originalRequest.newBuilder()
                    .header("User-Agent", "Mobile App Agent")
                    .build()
                chain.proceed(requestWithUserAgent)
            })

        gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()

        service = retrofit.create(EtherscanServiceAPI::class.java)
    }

    fun getTransactionList(address: Address, startBlock: Long): Single<EtherscanResponse> {
        return accountApi(
            action = "txList",
            address = address.hex,
            startBlock = startBlock
        ).map {
            parseResponse(it)
        }.retryWhenError(RequestError.RateLimitExceed::class)
    }

    fun getInternalTransactionList(address: Address, startBlock: Long): Single<EtherscanResponse> {
        return accountApi(
            action = "txlistinternal",
            address = address.hex,
            startBlock = startBlock
        ).map {
            parseResponse(it)
        }.retryWhenError(RequestError.RateLimitExceed::class)
    }

    fun getTokenTransactions(address: Address, startBlock: Long): Single<EtherscanResponse> {
        return accountApi(
            action = "tokentx",
            address = address.hex,
            startBlock = startBlock
        ).map {
            parseResponse(it)
        }.retryWhenError(RequestError.RateLimitExceed::class)
    }

    fun getInternalTransactionsAsync(transactionHash: ByteArray): Single<EtherscanResponse> {
        return accountApi(
            action = "txlistinternal",
            txHash = transactionHash.toHexString()
        ).map {
            parseResponse(it)
        }.retryWhenError(RequestError.RateLimitExceed::class)
    }

    fun getEip721Transactions(address: Address, startBlock: Long): Single<EtherscanResponse> {
        return accountApi(
            action = "tokennfttx",
            address = address.hex,
            startBlock = startBlock
        ).map {
            parseResponse(it)
        }.retryWhenError(RequestError.RateLimitExceed::class)
    }

    fun getEip1155Transactions(address: Address, startBlock: Long): Single<EtherscanResponse> {
        return accountApi(
            action = "token1155tx",
            address = address.hex,
            startBlock = startBlock
        ).map {
            parseResponse(it)
        }.retryWhenError(RequestError.RateLimitExceed::class)
    }

    private fun parseResponse(response: JsonElement): EtherscanResponse {
        try {
            val responseObj = response.asJsonObject
            val status = responseObj["status"].asJsonPrimitive.asString
            val message = responseObj["message"].asJsonPrimitive.asString

            if (status == "0" && message != "No transactions found") {
                val result = responseObj["result"].asJsonPrimitive.asString
                if (message == "NOTOK" && result == "Max rate limit reached") {
                    throw RequestError.RateLimitExceed()
                }
            }
            val result: List<Map<String, String>> = gson.fromJson(responseObj["result"], object : TypeToken<List<Map<String, String>>>() {}.type)
            return EtherscanResponse(status, message, result)

        } catch (rateLimitExceeded: RequestError.RateLimitExceed) {
            throw rateLimitExceeded
        } catch (err: Throwable) {
            throw RequestError.ResponseError("Unexpected response: $response")
        }
    }

    open class RequestError(message: String? = null) : Exception(message ?: "") {
        class ResponseError(message: String) : RequestError(message)
        class RateLimitExceed : RequestError()
    }

    private interface EtherscanServiceAPI {
        // ✅ API V1 - для сетей с собственными endpoints (Polygon, Avalanche, и т.д.)
        @GET("/api")
        fun accountApiV1(
            @Query("module") module: String = "account",
            @Query("action") action: String,
            @Query("address") address: String? = null,
            @Query("txhash") txHash: String? = null,
            @Query("startblock") startBlock: Long? = null,
            @Query("endblock") endBlock: Long? = null,
            @Query("sort") sort: String? = "desc",
            @Query("apikey") apiKey: String
        ): Single<JsonElement>
        
        // ✅ API V2 - для Ethereum и BSC (единый endpoint с chainId)
        @GET("/v2/api")
        fun accountApiV2(
            @Query("chainid") chainId: Int,
            @Query("module") module: String = "account",
            @Query("action") action: String,
            @Query("address") address: String? = null,
            @Query("txhash") txHash: String? = null,
            @Query("startblock") startBlock: Long? = null,
            @Query("endblock") endBlock: Long? = null,
            @Query("sort") sort: String? = "desc",
            @Query("apikey") apiKey: String
        ): Single<JsonElement>
    }
    
    // Универсальный метод, который выбирает V1 или V2 в зависимости от chainId
    private fun accountApi(
        action: String,
        address: String? = null,
        txHash: String? = null,
        startBlock: Long? = null,
        endBlock: Long? = null
    ): Single<JsonElement> {
        return if (useApiV2) {
            logger.info("Using API V2 for action=$action, chainId=$chainId")
            service.accountApiV2(
                chainId = chainId,
                action = action,
                address = address,
                txHash = txHash,
                startBlock = startBlock,
                endBlock = endBlock,
                apiKey = apiKey
            )
        } else {
            logger.info("Using API V1 for action=$action")
            service.accountApiV1(
                action = action,
                address = address,
                txHash = txHash,
                startBlock = startBlock,
                endBlock = endBlock,
                apiKey = apiKey
            )
        }
    }

}
