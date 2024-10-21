package com.example.testovoe.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testovoe.Data.model.Vacancy
import com.example.testovoe.R
import com.example.testovoe.databinding.VacancyCardItemLayoutBinding
import java.time.LocalDate

class VacanCardsAdapter(val vacancies: List<Vacancy>, val mode: String) : ListAdapter<Vacancy,CardVH>(VacanciesDiffCallback()){

    var changeFavoriteStatus: ((Vacancy)->Unit)? = null
    var id = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardVH {
        val binding = VacancyCardItemLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return CardVH(binding)
    }

    override fun getItemCount(): Int {
       return if (mode == MODE_MAIN_SCREEN){
            vacancies.take(3).size
        } else vacancies.size

    }

    override fun onBindViewHolder(holder: CardVH, position: Int) {
        val item = vacancies[position]
        with(holder.binding){
            lookingNow.text = "Сейчас просматривает ${item.lookingNumber} человек"
            vacancyTitle.text = item.title
            vacancyTown.text = item.address.town
            salary.text = item.salary.short
            companyTitle.text = item.company
            experience.text = item.experience.previewText
            whenPublish.text = item.publishedDate

            if (item.salary.short == null){
                salary.visibility = View.GONE
            } else {
                salary.visibility = View.VISIBLE
            }

            if (item.isFavorite == true){
                favoriteBtn.setImageResource(R.drawable.favorite_active)
            } else {
                favoriteBtn.setImageResource(R.drawable.favorite_inactive)
            }

            favoriteBtn.setOnClickListener {
                id = item.id
                changeFavoriteStatus?.invoke(item)
                notifyItemChanged(position)
            }
            println("salary ${item.salary.short}")
        }
    }
}

class CardVH(val binding: VacancyCardItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)