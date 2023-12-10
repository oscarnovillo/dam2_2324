package com.example.roomviewmodel.data

import androidx.room.*
import com.example.roomviewmodel.data.modelo.CosaEntity
import com.example.roomviewmodel.data.modelo.PersonaEntity
import com.example.roomviewmodel.data.modelo.PersonaWithCosas

@Dao
interface PersonaDao {

    @Query("SELECT * from personas ORDER BY nombre ASC")
    suspend fun getPersonas(): List<PersonaEntity>

    @Query("SELECT * from personas ORDER BY nombre DESC")
    suspend fun getPersonasDes(): List<PersonaEntity>

    @Query("SELECT * from cosas")
    suspend fun getCosas(): List<CosaEntity>

    @Transaction
    @Query("SELECT * FROM personas WHERE id = :id")
    suspend fun getPersonaWithCosas(id: Int): PersonaWithCosas

    @Transaction
    @Query("SELECT * FROM personas")
    suspend fun getPersonaWithCosas(): List<PersonaWithCosas>

    @Query("SELECT * from personas WHERE id = :id")
    suspend fun getPersona(id: Int): PersonaEntity

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: PersonaEntity) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: CosaEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: List<CosaEntity>)

    @Transaction
    suspend fun createTransaction(personaCosas: PersonaWithCosas) {
        personaCosas.persona.id = insert(personaCosas.persona).toInt()
        personaCosas.cosa?.apply{
            forEach { it.personaId = personaCosas.persona.id  }
            insert(this)
        }
    }


    @Update
    fun update(item: PersonaEntity)

    @Delete
    fun delete(item: PersonaEntity)

}