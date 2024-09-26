package com.wallet0x.erc20kit.events

import com.wallet0x.ethereumkit.contracts.ContractEventInstance
import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger

class ApproveEventInstance(
    contractAddress: Address,
    val owner: Address,
    val spender: Address,
    val value: BigInteger
) : ContractEventInstance(contractAddress)
