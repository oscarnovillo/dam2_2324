package com.example.primeraapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.primeraapp.data.Data
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {


    lateinit var tvName: TextView
    lateinit var btText: Button
    lateinit var editText: EditText
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(

        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val d = Data("hh")
        d.user = "hh"



        tvName = this.findViewById(R.id.cajaTexto)
        btText = this.findViewById(R.id.button)
        editText = this.findViewById(R.id.editTextText)



        btText.setOnClickListener {
            Log.w(":::TAG", "onCreate: ")
            if (tvName.text == "Hola mundo") {
                Toast.makeText(this, "Hola ${tvName.text}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No has escrito nada", Toast.LENGTH_SHORT).show()
            }

            tvName.text = R.string.hello_world.toString()
            viewModel.onClick(editText.text.toString() )
        }

        viewModel.uiState.observe(this@MainActivity) { state ->

            editText.setText(state.name)

        }


    }



}