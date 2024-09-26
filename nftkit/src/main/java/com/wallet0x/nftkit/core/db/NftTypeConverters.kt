package com.wallet0x.nftkit.core.db

import androidx.room.TypeConverter
import com.wallet0x.nftkit.models.NftType

class NftTypeConverters {
    @TypeConverter
    fun nftTypeFromString(string: String?): NftType? {
        return string?.let { enumValueOf<NftType>(it) }
    }

    @TypeConverter
    fun nftTypeToString(nftType: NftType?): String? {
        return nftType?.name
    }
}