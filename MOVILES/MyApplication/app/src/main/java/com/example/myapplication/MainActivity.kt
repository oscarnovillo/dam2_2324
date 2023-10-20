package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    lateinit var txt: EditText;
    lateinit var boton: Button;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt = findViewById(R.id.editTextText)
        txt.setBackgroundColor(getColor(R.color.rojito))
        txt.setText("hola")

        boton = findViewById(R.id.button)
        boton.setOnClickListener {

            Toast.makeText(this,"la caja contiene $ ${txt.text}",Toast.LENGTH_SHORT).show()
        }


    }

    fun hacerAlgo()

    {

        txt.text
    }
}