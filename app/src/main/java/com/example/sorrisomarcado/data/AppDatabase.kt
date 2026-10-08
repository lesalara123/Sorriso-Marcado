package com.example.sorrisomarcado.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Consulta::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun consultaDao(): ConsultaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sorriso_marcado_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
