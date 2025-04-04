package com.guagua.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.guagua.data.card.local.CardDao
import com.guagua.data.card.local.CardEntity

@Database(
    version = 1,
    entities = [
        CardEntity::class
    ],
    exportSchema = false,
)
abstract class GuaPayDatabase : RoomDatabase() {
    internal abstract fun cardDao(): CardDao

    companion object {
        private var instance: GuaPayDatabase? = null
        fun getInstance(context: Context): GuaPayDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.inMemoryDatabaseBuilder(context, GuaPayDatabase::class.java)
                    .build()
                    .also { instance = it }
            }
        }
    }
}