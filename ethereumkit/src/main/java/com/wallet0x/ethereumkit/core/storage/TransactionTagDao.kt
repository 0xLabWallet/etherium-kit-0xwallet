package com.wallet0x.ethereumkit.core.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.wallet0x.ethereumkit.models.TransactionTag

@Dao
interface TransactionTagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tags: List<TransactionTag>)

}
