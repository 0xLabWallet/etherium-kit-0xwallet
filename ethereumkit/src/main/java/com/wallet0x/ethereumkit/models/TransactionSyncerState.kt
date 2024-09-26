package com.wallet0x.ethereumkit.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TransactionSyncerState(
    @PrimaryKey
    val syncerId: String,
    val lastBlockNumber: Long
)
