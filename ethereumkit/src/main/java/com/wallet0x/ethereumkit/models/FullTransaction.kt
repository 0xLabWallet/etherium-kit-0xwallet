package com.wallet0x.ethereumkit.models

import com.wallet0x.ethereumkit.decorations.TransactionDecoration

class FullTransaction(
    val transaction: Transaction,
    val decoration: TransactionDecoration
)
