package com.example.washcar.Config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.washcar.Dao.LavadoDao
import com.example.washcar.Models.Cliente
import com.example.washcar.Models.RegistroLavado
import com.example.washcar.Models.Servicio
import com.example.washcar.Models.Vehiculo

@Database(
    entities = [Cliente::class, RegistroLavado::class, Servicio::class, Vehiculo::class],
    version = 1
)
abstract class LavadoBD: RoomDatabase() {

    abstract val lavadoDao: LavadoDao

    companion object{
        const val DATABASE_NAME = "db_lavado"
    }
}
