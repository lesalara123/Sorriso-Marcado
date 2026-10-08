package com.example.sorrisomarcado

import android.app.Application
import com.example.sorrisomarcado.data.AppDatabase
import com.example.sorrisomarcado.data.ConsultaRepository

class SorrisoApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { ConsultaRepository(database.consultaDao()) }
}
