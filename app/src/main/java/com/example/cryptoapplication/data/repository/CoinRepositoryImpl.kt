package com.example.cryptoapplication.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.cryptoapplication.data.database.AppDatabase
import com.example.cryptoapplication.data.mapper.CoinInfoMapper
import com.example.cryptoapplication.data.network.ApiFactory
import com.example.cryptoapplication.domain.model.CoinInfo
import com.example.cryptoapplication.domain.repository.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    private val application: Application,
) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService

    private val mapper = CoinInfoMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> = coinInfoDao.getPriceList().map {
        it.map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> =
        coinInfoDao.getPriceInfoAboutCoin(fromSymbol).map {
            mapper.mapDbModelToEntity(it)
        }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }
}
