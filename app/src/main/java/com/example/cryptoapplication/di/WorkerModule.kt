package com.example.cryptoapplication.di

import androidx.work.ListenableWorker
import com.example.cryptoapplication.workers.ChildWorkerFactory
import com.example.cryptoapplication.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}