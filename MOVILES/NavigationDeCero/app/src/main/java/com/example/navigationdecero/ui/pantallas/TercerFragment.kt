package com.example.navigationdecero.ui.pantallas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationdecero.R
import com.example.navigationdecero.databinding.FragmentPrimerBinding
import com.example.navigationdecero.databinding.FragmentTercerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TercerFragment : Fragment() {


    private var _binding: FragmentTercerBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentTercerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val args : TercerFragmentArgs by navArgs()
            args?.mensaje?.let {
                texto.text = it
            }
            irPrimero.setOnClickListener {
                val action = TercerFragmentDirections.actionTercerFragmentToPrimerFragment("primero")
                findNavController().navigate(action)
            }
        }
    }


}