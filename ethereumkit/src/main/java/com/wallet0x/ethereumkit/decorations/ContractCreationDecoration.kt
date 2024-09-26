package com.wallet0x.ethereumkit.decorations

class ContractCreationDecoration : TransactionDecoration() {

    override fun tags(): List<String> = listOf("contractCreation")

}
