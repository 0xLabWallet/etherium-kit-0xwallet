package com.wallet0x.ethereumkit.spv.core.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wallet0x.ethereumkit.spv.models.AccountStateSpv

@Dao
interface AccountStateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(accountState: AccountStateSpv)

    @Query("SELECT * FROM AccountStateSpv LIMIT 1")
    fun getAccountState(): AccountStateSpv?
}
