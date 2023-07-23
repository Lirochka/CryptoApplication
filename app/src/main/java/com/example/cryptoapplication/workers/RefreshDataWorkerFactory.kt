package com.example.cryptoapplication.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.cryptoapplication.data.database.CoinInfoDao
import com.example.cryptoapplication.data.mapper.CoinInfoMapper
import com.example.cryptoapplication.data.network.ApiService
import javax.inject.Inject

class RefreshDataWorkerFactory @Inject constructor(
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinInfoMapper
): WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker? {
        return RefreshDataWorker(
            appContext,
            workerParameters,
            coinInfoDao,
            apiService,
            mapper
        )
    }
}