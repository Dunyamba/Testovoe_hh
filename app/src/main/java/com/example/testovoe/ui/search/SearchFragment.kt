package com.example.testovoe.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.testovoe.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var adapter: VacanCardsAdapter
    lateinit var recommendationsAdapter: RecommendationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val searchViewModel =
            ViewModelProvider(this,).get(SearchViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.vacancies.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { listVacancies ->
                    viewLifecycleOwner.lifecycleScope.launch {
                        searchViewModel.mode.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                            .collect { mode ->
                                adapter = VacanCardsAdapter(listVacancies, mode)
                                adapter.submitList(listVacancies)
                                binding.vacanCards.adapter = adapter
                                adapter.changeFavoriteStatus =
                                    { searchViewModel.changeStatusFavorite(adapter.id, ) }
                            }
                    }
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.offers.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { offers ->
                recommendationsAdapter = RecommendationsAdapter(offers)
                    binding.recommendationsBlock.adapter = recommendationsAdapter
                }
        }




        binding.moreVacanciesBtn.text = searchViewModel.setTextCountVacancies()

        binding.moreVacanciesBtn.setOnClickListener {
            searchViewModel.setModeVacanciesByCompliance()
        }

        binding.vacanciesCountTitle.text = searchViewModel.setTextCountVacanciesModeCompliance()

        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.mode.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect {
                if (it == MODE_MAIN_SCREEN) {
                    binding.recommendationsBlock.visibility = View.VISIBLE
                    binding.vacanciesForYouTitle.visibility = View.VISIBLE
                    binding.moreVacanciesBtn.visibility = View.VISIBLE
                    binding.vacanciesCountTitle.visibility = View.GONE
                } else if (it == MODE_VACANCIES_BY_COMPLIANCE) {
                    binding.recommendationsBlock.visibility = View.GONE
                    binding.vacanciesForYouTitle.visibility = View.GONE
                    binding.moreVacanciesBtn.visibility = View.GONE
                    binding.recommendations.visibility = View.GONE
                    binding.vacanciesCountTitle.visibility = View.VISIBLE
                    binding.vacanciesByComplianceTitle.visibility = View.VISIBLE
                    binding.arrowTop.visibility = View.VISIBLE
                    binding.arrowBottom.visibility = View.VISIBLE
                }
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}