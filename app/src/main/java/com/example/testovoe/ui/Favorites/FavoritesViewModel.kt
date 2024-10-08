package com.example.testovoe.ui.Favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testovoe.Data.Impl.TestImpl
import com.example.testovoe.Data.model.Vacancy
import com.example.testovoe.Domain.SetTextCountVacancies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.properties.Delegates

class FavoritesViewModel : ViewModel() {

    val testImpl = TestImpl

    private val _vacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancies = _vacancies.asStateFlow()
    var  moreVacanciesCount by Delegates.notNull<Int>()

    init {
        loadVacancies()
    }



    fun loadVacancies() {
        _vacancies.value = testImpl.loadData().vacancies.filter { it.isFavorite }
    }


        fun setTextCountVacancies(): String {
            moreVacanciesCount = _vacancies.value.size
            return when (moreVacanciesCount % 10) {
                1 -> "$moreVacanciesCount вакансия"
                2 -> "$moreVacanciesCount вакансии"
                3 -> "$moreVacanciesCount вакансии"
                4 -> "$moreVacanciesCount вакансии"
                else -> "$moreVacanciesCount вакансий"
            }
        }



}