package com.example.appnobasica.domain.usecases.personas

import com.example.appnobasica.data.Repository

class GetPersonas( private val repo : Repository){

    operator fun invoke() = repo.getPersonas()
}