package com.example.testovoe.ui.Auth

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.Editable
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testovoe.R
import com.example.testovoe.databinding.FragmentEnterScreenBinding
import kotlinx.coroutines.launch

class EnterScreenFragment : Fragment() {

    var _binding :FragmentEnterScreenBinding? = null
    val binding get() = _binding!!

    companion object {
        fun newInstance() = EnterScreenFragment()
    }

    private val viewModel: EnterScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterScreenBinding.inflate(layoutInflater,container, false )

        binding.btnContinue.setOnClickListener {
             if (viewModel.isMailValid()){
                        binding.hintError.visibility = View.INVISIBLE
                        binding.errorText.visibility = View.INVISIBLE
                        findNavController().navigate(R.id.replace_to_main_screen)
                    }  else {
                        binding.hintError.visibility = View.VISIBLE
                        binding.errorText.visibility = View.VISIBLE

                    }

        }

        binding.tilInputMail.addTextChangedListener {
            binding.hintError.visibility = View.INVISIBLE
            binding.errorText.visibility = View.INVISIBLE
            viewModel.emailIsEntered(it.toString())
        }

        fun convert(context: Context, value: Int): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value.toFloat(),
                context.resources.displayMetrics
            ).toInt()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.emailState.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED).collect{
                binding.btnContinue.isEnabled = it
                binding.clearTextIcon.isVisible = it
                if (it == true){
                    binding.hintIcon.visibility = View.GONE
                    binding.tilInputMail.setPadding(convert(requireContext(), 10),0 ,0,0)
                } else {
                    binding.hintIcon.visibility = View.VISIBLE
                    binding.tilInputMail.setPadding(convert(requireContext(), 40),0 ,0,0)
                }
            }
        }
        binding.clearTextIcon.setOnClickListener {

            binding.tilInputMail.setText("")
        }

        return binding.root


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}