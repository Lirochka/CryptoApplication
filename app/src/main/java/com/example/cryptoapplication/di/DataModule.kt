package com.example.cryptoapplication.di

import android.app.Application
import com.example.cryptoapplication.data.database.AppDatabase
import com.example.cryptoapplication.data.database.CoinInfoDao
import com.example.cryptoapplication.data.repository.CoinRepositoryImpl
import com.example.cryptoapplication.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl) : CoinRepository

    companion object{

        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao{
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}