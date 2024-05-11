package com.example.washcar.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Servicio (
    @PrimaryKey
    @ColumnInfo(name = "ServicioID")
    var ServicioID: Long = 0,

    @ColumnInfo(name = "NombreS")
    var NombreS: String = "",

    @ColumnInfo(name = "Precio")
    var Precio: String = "",
)