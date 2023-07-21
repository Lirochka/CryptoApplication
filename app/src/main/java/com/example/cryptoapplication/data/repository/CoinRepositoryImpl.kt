package com.example.cryptoapplication.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoapplication.data.database.AppDatabase
import com.example.cryptoapplication.data.mapper.CoinInfoMapper
import com.example.cryptoapplication.domain.model.CoinInfo
import com.example.cryptoapplication.domain.repository.CoinRepository
import com.example.cryptoapplication.workers.RefreshDataWorker

class CoinRepositoryImpl(
    private val application: Application,
) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
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
    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.APPEND,
            RefreshDataWorker.makeRequest()
        )
    }
}
