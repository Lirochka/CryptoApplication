package com.example.cryptoapplication.presentation

import androidx.lifecycle.ViewModel
import com.example.cryptoapplication.domain.usecase.GetCoinInfoListUseCase
import com.example.cryptoapplication.domain.usecase.GetCoinInfoUseCase
import com.example.cryptoapplication.domain.usecase.LoadDataUseCase
import javax.inject.Inject


class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}
