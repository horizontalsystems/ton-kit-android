package io.horizontalsystems.tonkit.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.horizontalsystems.tonkit.models.Account
import io.horizontalsystems.tonkit.models.Event
import io.horizontalsystems.tonkit.models.EventSyncState
import io.horizontalsystems.tonkit.models.JettonBalance
import io.horizontalsystems.tonkit.models.Tag

@Database(
    entities = [
        Account::class,
        JettonBalance::class,
        Event::class,
        EventSyncState::class,
        Tag::class,
    ],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class KitDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun jettonDao(): JettonDao
    abstract fun eventDao(): EventDao

    companion object {
        fun getInstance(context: Context, name: String): KitDatabase {
            return Room
                .databaseBuilder(context, KitDatabase::class.java, name)
                .allowMainThreadQueries()
                .build()
        }
    }
}
