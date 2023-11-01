package com.example.recyclerview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R

import com.example.recyclerview.data.EjemploRepository
import com.example.recyclerview.domain.model.Persona
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class ReciclerActivity : AppCompatActivity() {


    private fun click(nombre:String){
        Snackbar.make(findViewById<RecyclerView>(R.id.rvPersonas)
            , " $nombre", Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recicler)

        intent.extras?.let {
//            val persona = it.getParcelable<Persona>(getString(R.string.persona))
//            Timber.i("Nombre: ${persona}")
//            Log.i("MITAG", "Nombre: ${persona}")
        }

        val listaPersonas = EjemploRepository(assets.open("data.json")).getLista()
        Toast.makeText(this , "el nombre es ${listaPersonas[0].name}", Toast.LENGTH_SHORT).show()

        val rvPersona = this.findViewById<RecyclerView>(R.id.rvPersonas)

        Snackbar.make(rvPersona, " ${listaPersonas[0].name} ", Snackbar.LENGTH_SHORT).show()
        var adapter = PersonasAdapter(listaPersonas, ::click)

        listaPersonas.let {
            rvPersona.adapter = adapter
            rvPersona.layoutManager = GridLayoutManager(this@ReciclerActivity,2)
        }



    }



}