package com.example.cryptoapplication.domain.repository

import androidx.lifecycle.LiveData
import com.example.cryptoapplication.domain.model.CoinInfo

interface CoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfo>>

    fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo>

    suspend fun loadData()
}