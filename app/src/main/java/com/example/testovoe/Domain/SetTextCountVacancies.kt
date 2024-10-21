package com.example.testovoe.Domain

class SetTextCountVacancies {

    fun set(size: Int): String {
       var moreVacanciesCount = size
        return when (moreVacanciesCount % 10) {
            1 -> "Еще $moreVacanciesCount вакансия"
            2 -> "Еще $moreVacanciesCount вакансии"
            3 -> "Еще $moreVacanciesCount вакансии"
            4 -> "Еще $moreVacanciesCount вакансии"
            else -> "Еще $moreVacanciesCount вакансий"
        }
    }
}