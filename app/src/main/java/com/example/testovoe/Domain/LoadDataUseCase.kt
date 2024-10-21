package com.example.testovoe.Domain

import com.example.testovoe.Data.model.MainModel

class LoadDataUseCase (private val testRepo: TestRepo) {
    fun load() : MainModel {
       return testRepo.loadData()
    }
}