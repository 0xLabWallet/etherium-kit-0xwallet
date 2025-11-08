package com.wallet0x.ethereumkit.spv.net

import com.wallet0x.ethereumkit.core.ISpvStorage
import com.wallet0x.ethereumkit.core.hexStringToByteArray
import com.wallet0x.ethereumkit.network.INetwork
import com.wallet0x.ethereumkit.crypto.ECKey
import com.wallet0x.ethereumkit.spv.net.les.LESPeer

class PeerProvider(val connectionKey: ECKey,
                   val storage: ISpvStorage,
                   val network: INetwork) {

    fun getPeer(): LESPeer {
        val node = Node(id = "9cfc66931bd30d316b57c4e761a58110d882fc0a6387e26897499be4263cac7cbdb1a8ba43088b8b279ffa84db6c331e7968875191baeecf9d87c1221feec1eb".hexStringToByteArray(),
                host = "212.112.123.197",
                port = 30303,
                discoveryPort = 30301)

        return LESPeer.getInstance(connectionKey, node)
    }

}
