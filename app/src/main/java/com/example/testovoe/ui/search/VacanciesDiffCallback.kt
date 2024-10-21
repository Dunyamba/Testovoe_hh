package com.example.testovoe.ui.search

import androidx.recyclerview.widget.DiffUtil
import com.example.testovoe.Data.model.Vacancy

class VacanciesDiffCallback: DiffUtil.ItemCallback<Vacancy>() {
    override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem == newItem
    }
}