package com.wallet0x.erc20kit.core

import com.wallet0x.erc20kit.core.room.Erc20KitDatabase
import com.wallet0x.erc20kit.models.TokenBalance
import java.math.BigInteger

class Erc20Storage(
        database: Erc20KitDatabase
) : ITokenBalanceStorage {

    private val tokenBalanceDao = database.tokenBalanceDao

    override fun getBalance(): BigInteger? {
        return tokenBalanceDao.getBalance()?.value
    }

    override fun save(balance: BigInteger) {
        tokenBalanceDao.insert(TokenBalance(balance))
    }

}
