package com.example.appnobasica.domain.usecases.personas

import com.example.appnobasica.data.Repository

class GetPersonas {

    operator fun invoke() = Repository.getInstance().getPersonas()
}