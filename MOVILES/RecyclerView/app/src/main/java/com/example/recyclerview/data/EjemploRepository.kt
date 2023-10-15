package com.example.recyclerview.data

import com.example.recyclerview.data.model.Ejemplo
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter





class EjemploRepository(file: InputStream? = null) {


    class LocalDateTimeAdapter {
        @ToJson
        fun toJson(value: LocalDateTime): String {
            return FORMATTER.format(value)
        }

        @FromJson
        fun fromJson(value: String): LocalDateTime {
            return LocalDateTime.from(FORMATTER.parse(value))
        }

        companion object {
            private val FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss-SSSS")
        }
    }


    companion object{
        private val lista = mutableListOf<Ejemplo>()

    }


    init {
        if (lista.size == 0)
        {
            val moshi = Moshi.Builder()
                .add(LocalDateTimeAdapter())
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val listOfCardsType: Type = Types.newParameterizedType(
                MutableList::class.java,
                Ejemplo::class.java
            )
            val ejemplo = file?.bufferedReader()?.readText()?.let {
                moshi.adapter<List<Ejemplo>>(listOfCardsType)
                    .fromJson(it)
            }

            ejemplo?.let{ lista.addAll(it)}
        }
    }

    fun getLista() : List<Ejemplo>{
        //return lista.map { it.toPersona() }
        return lista
    }


    fun getEjemplo(id:Int) : Ejemplo{
        //return lista.map { it.toPersona() }
        return lista[id]
    }

    fun addEjemplo(ejemplo: Ejemplo)
    {
        lista.add(ejemplo)
    }

}