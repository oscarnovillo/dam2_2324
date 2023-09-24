package com.example.appnobasica.domain.usecases.personas

import com.example.appnobasica.data.RespositoryDos
import com.example.appnobasica.domain.modelo.Persona

class AddPersonaUseCase {

    operator fun invoke(persona: Persona) =
        RespositoryDos.addPersona(persona)

}