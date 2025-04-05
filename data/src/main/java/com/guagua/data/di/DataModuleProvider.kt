package com.guagua.data.di

import androidx.room.Room
import com.guagua.data.card.CardRepository
import com.guagua.data.card.local.CardLocalDataSource
import com.guagua.data.card.remote.CardRemoteDataSource
import com.guagua.data.db.GuaPayDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object DataModuleProvider {

    val dataModule = module {
        single {
            Room.databaseBuilder(get(), GuaPayDatabase::class.java, "GuaPayDatabase")
                .build()
        }
        single { get<GuaPayDatabase>().cardDao() }

        singleOf(::CardRemoteDataSource)
        singleOf(::CardLocalDataSource)
        singleOf(::CardRepository)
    }
}