package com.example.testovoe.ui.Favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testovoe.Data.model.Vacancy
import com.example.testovoe.R
import com.example.testovoe.databinding.VacancyCardItemLayoutBinding
import com.example.testovoe.ui.search.CardVH
import com.example.testovoe.ui.search.MODE_MAIN_SCREEN


    class FavoritesAdapter(val vacancies: List<Vacancy>,) : RecyclerView.Adapter<CardVH>(){


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardVH {
            val binding = VacancyCardItemLayoutBinding.inflate(LayoutInflater.from(parent.context))
            return CardVH(binding)
        }

        override fun getItemCount(): Int =vacancies.size


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
                println("salary ${item.salary.short}")
            }
        }


}