package com.wallet0x.ethereumkit.spv.core.storage

import com.wallet0x.ethereumkit.core.ISpvStorage
import com.wallet0x.ethereumkit.spv.models.AccountStateSpv
import com.wallet0x.ethereumkit.spv.models.BlockHeader

class SpvStorage(private val database: SpvDatabase) : ISpvStorage {

    override fun getLastBlockHeader(): BlockHeader? {
        return database.blockHeaderDao().getAll().firstOrNull()
    }

    override fun saveBlockHeaders(blockHeaders: List<BlockHeader>) {
        return database.blockHeaderDao().insertAll(blockHeaders)
    }

    override fun getBlockHeadersReversed(fromBlockHeight: Long, limit: Int): List<BlockHeader> {
        return database.blockHeaderDao().getByBlockHeightRange(fromBlockHeight - limit, fromBlockHeight)
    }

    override fun getAccountState(): AccountStateSpv? {
        return database.accountStateDao().getAccountState()
    }

    override fun saveAccountSate(accountState: AccountStateSpv) {
        database.accountStateDao().insert(accountState)
    }

}
