package com.example.testovoe.ui.search

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testovoe.Data.Impl.TestImpl
import com.example.testovoe.Data.model.Offer
import com.example.testovoe.Data.model.Vacancy
import com.example.testovoe.Domain.ChangeFavoriteStatusUseCase
import com.example.testovoe.Domain.LoadDataUseCase
import com.example.testovoe.Domain.SetTextCountVacancies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.properties.Delegates

const val MODE_VACANCIES_BY_COMPLIANCE = "Vacancies by Compliance"
const val MODE_MAIN_SCREEN = "main screen"

class SearchViewModel() : ViewModel() {

    val testImpl = TestImpl

    val loadDataUseCase = LoadDataUseCase(testImpl)
    val changeFavoriteStatusUseCase = ChangeFavoriteStatusUseCase(testImpl)

    private val _vacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancies = _vacancies.asStateFlow()
    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers = _offers.asStateFlow()
    var  moreVacanciesCount by Delegates.notNull<Int>()
    private val _mode = MutableStateFlow<String>(MODE_MAIN_SCREEN)
    val mode = _mode.asStateFlow()
    val setTextCountVacancies = SetTextCountVacancies()



    init {
        loadVacancies()
        loadOffers()
    }

    fun loadVacancies() {
        _vacancies.value = loadDataUseCase.load().vacancies
    }

    fun changeStatusFavorite(id: String){
        _vacancies.value=changeFavoriteStatusUseCase.change(_vacancies.value.toMutableList(),id )
    }

    fun loadOffers() {
        _offers.value = loadDataUseCase.load().offers
    }

    fun setTextCountVacancies(): String {
       return setTextCountVacancies.set(_vacancies.value.size)
    }

    fun setTextCountVacanciesModeCompliance(): String {
        moreVacanciesCount = _vacancies.value.size
        return when (moreVacanciesCount % 10) {
            1 -> "$moreVacanciesCount вакансия"
            2 -> "$moreVacanciesCount вакансии"
            3 -> "$moreVacanciesCount вакансии"
            4 -> "$moreVacanciesCount вакансии"
            else -> "$moreVacanciesCount вакансий"
        }
    }

    fun setModeMainScreen(){
        _mode.value = MODE_MAIN_SCREEN
    }


    fun setModeVacanciesByCompliance(){
        _mode.value = MODE_VACANCIES_BY_COMPLIANCE
    }


}