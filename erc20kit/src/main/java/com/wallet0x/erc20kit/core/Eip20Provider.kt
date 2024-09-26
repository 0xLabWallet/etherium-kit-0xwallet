package com.wallet0x.erc20kit.core

import com.wallet0x.erc20kit.contract.DecimalsMethod
import com.wallet0x.erc20kit.contract.NameMethod
import com.wallet0x.erc20kit.contract.SymbolMethod
import com.wallet0x.erc20kit.events.TokenInfo
import com.wallet0x.ethereumkit.api.core.IRpcApiProvider
import com.wallet0x.ethereumkit.api.core.RpcBlockchain
import com.wallet0x.ethereumkit.contracts.ContractMethodHelper
import com.wallet0x.ethereumkit.core.EthereumKit
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.DefaultBlockParameter
import com.wallet0x.ethereumkit.models.RpcSource
import com.wallet0x.ethereumkit.spv.core.toBigInteger
import io.reactivex.Single

class Eip20Provider(private val provider: IRpcApiProvider) {

    class TokenNotFoundException : Throwable()

    fun getTokenInfo(contractAddress: Address): Single<TokenInfo> {
        val nameSingle = getTokenName(contractAddress)
        val symbolSingle = getTokenSymbol(contractAddress)
        val decimalsSingle = getDecimals(contractAddress)

        return Single
            .zip(nameSingle, symbolSingle, decimalsSingle) { name, symbol, decimals ->
                TokenInfo(name, symbol, decimals)
            }
    }

    private fun getDecimals(contractAddress: Address): Single<Int> {
        val callRpc = RpcBlockchain.callRpc(
            contractAddress,
            DecimalsMethod().encodedABI(),
            DefaultBlockParameter.Latest
        )

        return provider.single(callRpc)
            .map {
                if (it.isEmpty()) throw TokenNotFoundException()

                it.sliceArray(IntRange(0, 31)).toBigInteger().toInt()
            }
    }

    private fun getTokenSymbol(contractAddress: Address): Single<String> {
        val callRpc = RpcBlockchain.callRpc(
            contractAddress,
            SymbolMethod().encodedABI(),
            DefaultBlockParameter.Latest
        )

        return provider.single(callRpc)
            .map {
                if (it.isEmpty()) throw TokenNotFoundException()

                val argumentTypes = listOf(ByteArray::class)

                val parsedArguments = ContractMethodHelper.decodeABI(it, argumentTypes)
                val stringBytes = parsedArguments[0] as? ByteArray ?: throw TokenNotFoundException()

                String(stringBytes)
            }
    }

    private fun getTokenName(contractAddress: Address): Single<String> {
        val callRpc = RpcBlockchain.callRpc(
            contractAddress,
            NameMethod().encodedABI(),
            DefaultBlockParameter.Latest
        )

        return provider.single(callRpc)
            .map {
                if (it.isEmpty()) throw TokenNotFoundException()

                val argumentTypes = listOf(ByteArray::class)

                val parsedArguments = ContractMethodHelper.decodeABI(it, argumentTypes)
                val stringBytes = parsedArguments[0] as? ByteArray ?: throw TokenNotFoundException()

                String(stringBytes)
            }
    }

    companion object {

        fun instance(rpcSource: RpcSource.Http): Eip20Provider {
            return Eip20Provider(EthereumKit.getNodeApiProvider(rpcSource))
        }

    }

}