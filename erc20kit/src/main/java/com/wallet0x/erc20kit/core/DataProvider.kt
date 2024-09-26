package com.wallet0x.erc20kit.core

import com.wallet0x.erc20kit.contract.BalanceOfMethod
import com.wallet0x.ethereumkit.core.EthereumKit
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.spv.core.toBigInteger
import io.reactivex.Single
import java.math.BigInteger

class DataProvider(
        private val ethereumKit: EthereumKit
) : IDataProvider {

    override fun getBalance(contractAddress: Address, address: Address): Single<BigInteger> {
        return ethereumKit.call(contractAddress, BalanceOfMethod(address).encodedABI())
                .map { it.sliceArray(IntRange(0, 31)).toBigInteger() }
    }

}
