package com.example.navigationdecero.ui.pantallas

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.navigationdecero.R
import com.example.navigationdecero.databinding.FragmentSegundoBinding


class SegundoFragment : Fragment(),MenuProvider {

    private var _binding : FragmentSegundoBinding? = null
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentSegundoBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    //menuprovider
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_segundo_fragment, menu)

        val actionSearch = menu.findItem(R.id.search).actionView as SearchView

        actionSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                newText?.let {
                    binding.texto.setText(newText)
                }

                return false
            }


        })
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.primerFragment -> {
                val action = SegundoFragmentDirections.actionSegundoFragmentToPrimerFragment(binding.texto.text.toString())
                findNavController().navigate(action)
                true
            }
            R.id.otroTercerFragment -> {
                val action = SegundoFragmentDirections.meVoyDeSegundoATercero(binding.texto.text.toString())
                findNavController().navigate(action)
               // findNavController().navigate(R.id.action_segundoFragment_to_primerFragment)
                true
            }
            else -> false
        }
    }
}