package com.example.navigationdecero.ui.pantallas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationdecero.databinding.FragmentPrimerBinding


class PrimerFragment : Fragment() {


    private var _binding: FragmentPrimerBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPrimerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            irSegundo.setOnClickListener {
                val action = PrimerFragmentDirections.actionPrimerFragmentToSegundoFragment()
                findNavController().navigate(action)
            }
            irTercero.setOnClickListener{
                val action = PrimerFragmentDirections.actionPrimerFragmentToTercerFragment(null)
                findNavController().navigate(action)

            }
        }
    }
}