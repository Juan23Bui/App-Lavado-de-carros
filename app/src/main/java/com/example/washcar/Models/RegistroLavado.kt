package com.example.washcar.Models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = Vehiculo::class, parentColumns = ["VehiculoID"], childColumns = ["VehiculoID"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
    ForeignKey(entity = Servicio::class, parentColumns = ["ServicioID"], childColumns = ["ServicioID"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
])

class RegistroLavado(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "RegistroID")
    var RegistroID: Long = 0,

    @ColumnInfo(name = "VehiculoID")
    var VehiculoID: Long = 0,

    @ColumnInfo(name = "ServicioID")
    var ServicioID: Long = 0,

    @ColumnInfo(name = "FechaLavado")
    var FechaLavado: String = String(),

    @ColumnInfo(name = "HoraInicio")
    var HoraInicio: String = String(),

    @ColumnInfo(name = "HoraFin")
    var HoraFin: String = String(),

    @ColumnInfo(name = "PrecioTotal")
    var PrecioTotal: String = String(),
)