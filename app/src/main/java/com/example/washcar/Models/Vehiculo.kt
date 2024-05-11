package com.example.washcar.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
        ForeignKey(entity = Cliente::class, parentColumns = ["ClienteID"], childColumns = ["ClienteID"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
    ])

public class Vehiculo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "VehiculoID")
    var VehiculoID: Long = 0,

    @ColumnInfo(name = "ClienteID")
    var ClienteID: Long? = 0,

    @ColumnInfo(name = "Marca")
    var Marca: String = "",

    @ColumnInfo(name = "Modelo")
    var Modelo: String = "",

    @ColumnInfo(name = "Placa")
    var Placa: String = "",

    @ColumnInfo(name = "Color")
    var Color: String = "",

    @ColumnInfo(name = "Tipo")
    var Tipo: String = "",

)