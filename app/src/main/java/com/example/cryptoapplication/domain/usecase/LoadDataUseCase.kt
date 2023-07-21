package com.example.cryptoapplication.domain.usecase

import com.example.cryptoapplication.domain.repository.CoinRepository


class LoadDataUseCase(
    private val repository: CoinRepository
) {

    suspend operator fun invoke() = repository.loadData()
}