package com.guagua.data.card.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CardDao {

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    fun upsert(card: CardEntity)

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    fun upsert(cards: List<CardEntity>)

    @Query("SELECT * FROM CardEntity")
    fun getCardsFlow(): Flow<List<CardEntity>>

    @Query("DELETE FROM CardEntity")
    fun clear()
}