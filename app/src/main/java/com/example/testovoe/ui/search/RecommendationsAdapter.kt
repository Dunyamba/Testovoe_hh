package com.example.testovoe.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testovoe.Data.model.Offer
import com.example.testovoe.R
import com.example.testovoe.databinding.RecomendationsItemBinding
import com.example.testovoe.databinding.RecommendationsItemBinding

class RecommendationsAdapter(val listRecommendations: List<Offer>) :
    RecyclerView.Adapter<RecommendationsAdapterVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationsAdapterVH {
        val binding = RecomendationsItemBinding.inflate(LayoutInflater.from(parent.context))
        return RecommendationsAdapterVH(binding)
    }

    fun setIcon(id: String?): Int?{
        return when(id){
            "near_vacancies" -> R.drawable.near_vacancies
            "level_up_resume" -> R.drawable.level_up_resume
            "temporary_job" -> R.drawable.temporary_job
            else -> null
        }
    }

    override fun onBindViewHolder(holder: RecommendationsAdapterVH, position: Int) {
        with(holder.binding){
            val item = listRecommendations[position]
            itemText.text = item.title
            itemBtn.text = item.button?.text ?: ""
            if (item.button == null){
                itemBtn.visibility = View.GONE
            } else View.VISIBLE
            setIcon(item.id)?.let { itemLogo.setImageResource(it) }


        }
    }

    override fun getItemCount(): Int = listRecommendations.size
}

class RecommendationsAdapterVH(val binding: RecomendationsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

}