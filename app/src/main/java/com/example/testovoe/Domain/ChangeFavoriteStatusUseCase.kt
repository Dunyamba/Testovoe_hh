package com.example.testovoe.Domain

import com.example.testovoe.Data.model.Vacancy

class ChangeFavoriteStatusUseCase(private val testRepo: TestRepo) {
    fun change(listVacancy: MutableList<Vacancy>, id: String, ) : List<Vacancy> {
         return testRepo.changeFavoriteStatus(listVacancy, id)
    }
}