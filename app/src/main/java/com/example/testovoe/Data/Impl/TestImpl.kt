package com.example.testovoe.Data.Impl

import com.example.testovoe.Data.model.MainModel
import com.example.testovoe.Data.model.Offer
import com.example.testovoe.Data.model.Vacancy
import com.example.testovoe.Domain.TestRepo
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.BufferedReader
import java.io.InputStreamReader
import java.time.ZoneId

object TestImpl: TestRepo {

    private val vacancyList = sortedSetOf<Vacancy>({ o1, o2 ->
        o1.id compareTo o2.id})
    fun parseJson(content: String): MainModel {
        val gson = Gson()
        return gson.fromJson(content, MainModel::class.java)
    }
    fun loadJsonFromResources(fileName: String): String? {
        val classLoader = this::class.java.classLoader
        val inputStream = classLoader.getResourceAsStream(fileName)

        return if (inputStream != null) {
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.use { it.readText() }
        } else {
            println("Файл не найден!")
            null
        }
    }
    // Пример использования
    val jsonContent = loadJsonFromResources("mok_json_3.json")


    override fun loadData(): MainModel {
        return  parseJson(jsonContent ?: "")

    }

    override fun changeFavoriteStatus(listVacancy: MutableList<Vacancy>, id: String) : List<Vacancy> {
        val item =listVacancy.find { it.id==id }
        val newItem = item?.copy(isFavorite = !item.isFavorite)
        listVacancy.remove(item)

        newItem?.let { listVacancy.add(it) }

        return listVacancy.sortedBy { it.id }
    }
}