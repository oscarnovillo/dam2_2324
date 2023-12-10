package com.example.roomviewmodel.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "cosas",
foreignKeys = [
    ForeignKey(entity = PersonaEntity::class,
        parentColumns = ["id"],
        childColumns = ["personaId"])
        ])
data class CosaEntity(
    @ColumnInfo(name = "nombre")
    val nombre: String,
    var personaId: Int=0,
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
)