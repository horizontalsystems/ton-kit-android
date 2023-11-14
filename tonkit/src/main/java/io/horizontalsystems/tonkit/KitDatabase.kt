package io.horizontalsystems.tonkit

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.math.BigDecimal

@Database(entities = [TonTransaction::class], version = 1)
@TypeConverters(RoomTypeConverters::class)
abstract class KitDatabase : RoomDatabase() {
    abstract fun tonTransactionDao(): TonTransactionDao
}

class RoomTypeConverters {
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal) = value.toString()

    @TypeConverter
    fun toBigDecimal(value: String) = value.toBigDecimal()
}
