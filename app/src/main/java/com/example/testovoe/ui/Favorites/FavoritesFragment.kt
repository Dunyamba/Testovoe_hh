package com.example.testovoe.ui.Favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.testovoe.databinding.FragmentDashboardBinding
import com.example.testovoe.ui.search.VacanCardsAdapter
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var adapter: FavoritesAdapter
    lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesViewModel =
            ViewModelProvider(this).get(FavoritesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewLifecycleOwner.lifecycleScope.launch {
            favoritesViewModel.vacancies.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { listVacancies ->
                    adapter = FavoritesAdapter(listVacancies)
                    binding.vacanCards.adapter = adapter
                }
        }

        binding.vacanciesCountTitle.text = favoritesViewModel.setTextCountVacancies()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}