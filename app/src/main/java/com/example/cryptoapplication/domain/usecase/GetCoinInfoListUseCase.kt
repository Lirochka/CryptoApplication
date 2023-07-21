package com.example.cryptoapplication.domain.usecase

import com.example.cryptoapplication.domain.repository.CoinRepository

class GetCoinInfoListUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke() = repository.getCoinInfoList()
}