package io.horizontalsystems.tonkit.tonconnect

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface KeyValueDao {
    fun set(k: String, v: String) {
        save(KeyValue(k, v))
    }

    fun get(k: String): String? {
        TODO("Not yet implemented")
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(keyValue: KeyValue)


    fun get(k:String)

}
