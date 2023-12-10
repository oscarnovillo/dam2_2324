package com.example.roomviewmodel.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity(tableName = "personas")
data class PersonaEntity(

    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "nacimiento")
    val nacimiento: LocalDate,
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
)