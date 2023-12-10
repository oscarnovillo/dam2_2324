package com.example.roomviewmodel.data.modelo

import androidx.room.Embedded
import androidx.room.Relation


data class PersonaWithCosas(
    @Embedded val persona: PersonaEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "personaId"
    )
    val cosa: List<CosaEntity>?

)