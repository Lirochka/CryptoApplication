package com.example.cryptoapplication.domain.usecase

import com.example.cryptoapplication.domain.repository.CoinRepository

class GetCoinInfoUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol)
}