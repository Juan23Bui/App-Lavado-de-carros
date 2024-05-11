package com.example.washcar.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Cliente (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ClienteID")
    var ClienteID: Long = 0,

    @ColumnInfo(name = "Nombre")
    var Nombre: String = "",

    @ColumnInfo(name = "Apellido")
    var Apellido: String = "",

    @ColumnInfo(name = "Telefono")
    var Telefono: String = "",

    @ColumnInfo(name = "Email")
    var Email: String = "",

    @ColumnInfo(name = "Direccion")
    var Direccion: String = "",
)
