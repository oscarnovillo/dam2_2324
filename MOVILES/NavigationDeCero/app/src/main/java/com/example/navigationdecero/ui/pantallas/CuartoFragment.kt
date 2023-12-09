package com.example.navigationdecero.ui.pantallas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationdecero.R
import com.example.navigationdecero.databinding.FragmentCuartoBinding
import com.example.navigationdecero.databinding.FragmentPrimerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CuartoFragment : Fragment() {


    private var _binding: FragmentCuartoBinding? = null
    private val binding get() = _binding!!



    val args: CuartoFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCuartoBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            textView.setText(args.test)
        }
    }

}