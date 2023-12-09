package com.example.navigationdecero.ui.pantallas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationdecero.databinding.FragmentPrimerBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PrimerFragment : Fragment() {


    private lateinit var adapter: MiAdapter
    private var _binding: FragmentPrimerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PrimerFragmentViewModel by viewModels()

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

            adapter = MiAdapter(object : MiAdapter.MiActions {


                override fun itemHasClicked(cadena: String) {
                    Snackbar.make(requireView(), cadena, Snackbar.LENGTH_SHORT).show()
                }
            })
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            viewModel.uiState.observe(viewLifecycleOwner) {
                adapter.submitList(it.cadenas)
            }

            irSegundo.setOnClickListener {
                val action = PrimerFragmentDirections.actionPrimerFragmentToSegundoFragment()
                findNavController().navigate(action)
            }
            irTercero.setOnClickListener{
                val action = PrimerFragmentDirections.actionPrimerFragmentToTercerFragment(null)
                findNavController().navigate(action)

            }
            irCuarto.setOnClickListener{
                val action = PrimerFragmentDirections.actionPrimerFragmentToCuartoFragment("desde primero")
                findNavController().navigate(action)

            }
        }
    }
}