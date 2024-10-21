package com.example.testovoe.Domain

import com.example.testovoe.Data.model.MainModel
import com.example.testovoe.Data.model.Vacancy

interface TestRepo {

    fun loadData() : MainModel

    fun changeFavoriteStatus(listVacancy: MutableList<Vacancy>, id: String,) : List<Vacancy>
}