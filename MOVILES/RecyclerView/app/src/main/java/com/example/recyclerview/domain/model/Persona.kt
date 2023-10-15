package com.example.recyclerview.domain.model


import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Persona(val nombre: String, val apellidos: String, val edad: Int) : Parcelable
