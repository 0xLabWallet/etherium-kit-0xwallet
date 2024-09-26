package com.wallet0x.ethereumkit.sample.modules.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.wallet0x.ethereumkit.core.EthereumKit
import com.wallet0x.ethereumkit.sample.App
import com.wallet0x.nftkit.core.NftKit
import com.wallet0x.nftkit.models.NftBalance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NftsViewModel(
    private val nftKit: NftKit
) : ViewModel() {

    val nftBalancesFlow: Flow<List<NftBalance>>
        get() = nftKit.nftBalancesFlow

    init {
        viewModelScope.launch {
            nftKit.nftBalancesFlow.collect {
                Log.e("nft", "nftBalances: ${it.size}")
            }
        }

        nftKit.start()
    }
}

class NftsViewModelFactory(private val evmKit: EthereumKit) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val nftKit = NftKit.getInstance(com.wallet0x.ethereumkit.sample.App.instance, evmKit)
        nftKit.addEip1155TransactionSyncer()
        nftKit.addEip1155Decorators()
        nftKit.addEip721TransactionSyncer()
        nftKit.addEip721Decorators()

        return NftsViewModel(nftKit) as T
    }
}