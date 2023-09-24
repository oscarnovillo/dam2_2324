package com.example.appnobasica.utils

import android.content.Context
import androidx.annotation.StringRes
import com.example.appnobasica.domain.modelo.Persona

class StringProvider(val context: Context) {
    companion object {
        fun instance(context: Context): StringProvider = StringProvider(context)
    }

    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }

}