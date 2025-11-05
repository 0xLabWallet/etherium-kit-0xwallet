package com.wallet0x.erc20kit.core

import android.content.Context
import com.wallet0x.erc20kit.core.room.Erc20KitDatabase
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.Chain

internal object Erc20DatabaseManager {

    fun getErc20Database(context: Context, chain: Chain, walletId: String, contractAddress: Address): Erc20KitDatabase {
        return Erc20KitDatabase.getInstance(context, "${getDbNameBase(chain, walletId)}-${contractAddress.hex}")
    }

    fun clear(context: Context, chain: Chain, walletId: String) {
        synchronized(this) {
            val dbNameBase = getDbNameBase(chain, walletId)

            context.databaseList().forEach {
                if (it.contains(dbNameBase)) {
                    context.deleteDatabase(it)
                }
            }
        }
    }

    private fun getDbNameBase(chain: Chain, walletId: String): String {
        return "Erc20-${chain.id}-$walletId"
    }

}
