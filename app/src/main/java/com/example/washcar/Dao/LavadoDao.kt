package com.example.washcar.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.washcar.Models.Cliente
import com.example.washcar.Models.RegistroLavado
import com.example.washcar.Models.Servicio
import com.example.washcar.Models.Vehiculo

@Dao
interface LavadoDao {

    //Cliente
    @Query("SELECT * FROM cliente")
    fun obtenerCliente(): List<Cliente>

    @Query("SELECT * FROM cliente WHERE ClienteID = :clienteId")
    fun obtenerClientePorId(clienteId: Int): Cliente?

    @Insert
    fun insert (cliente: Cliente)

    @Query("SELECT * FROM cliente WHERE ClienteID = :clienteId")
    fun buscarCliente(clienteId: Int): Cliente?

    @Update
    fun actualizarCliente(cliente: Cliente)

    @Query("DELETE FROM cliente WHERE ClienteID = :clienteID")
    fun eliminarCliente(clienteID: Long)

    //Vehiculo
    @Query("SELECT * FROM vehiculo")
    fun obtenerVehiculo(): List<Vehiculo>

    @Query("SELECT * FROM vehiculo")
    fun obtenerTodosVehiculos(): List<Vehiculo>

    @Insert
    fun insertVehiculo(vehiculo: Vehiculo)

    @Query("SELECT * FROM vehiculo WHERE VehiculoID = :vehiculoId")
    fun buscarVehiculo(vehiculoId: Int): Vehiculo?

    @Update
    fun actualizarVehiculo(vehiculo: Vehiculo)

    @Query("DELETE FROM vehiculo WHERE VehiculoID = :vehiculoID")
    fun eliminarVehiculo(vehiculoID: Long)

    //Servicio
    @Query("SELECT * FROM servicio")
    fun obtenerServicio(): List<Servicio>

    @Query("SELECT * FROM Servicio WHERE ServicioID = :servicioID")
    fun obtenerServicioPorId(servicioID: Int): Servicio?

    @Insert
    fun insertServicio(servicio: Servicio)

    @Query("SELECT * FROM Servicio WHERE ServicioID = :servicioID")
    fun buscarServicio(servicioID: Int): Servicio

    @Update
    fun actualizarServicio(servicio: Servicio)

    @Query("DELETE FROM servicio WHERE ServicioID = :servicioID")
    fun eliminarServicio(servicioID: Int)

    //Registro
    @Query("SELECT * FROM RegistroLavado")
    fun obtenerRegistros(): List<RegistroLavado>

    @Query("SELECT * FROM RegistroLavado WHERE RegistroID = :registroID")
    fun obtenerRegistroPorId(registroID: Long): RegistroLavado?

    @Insert
    fun insertRegistro(registro: RegistroLavado)

    @Query("SELECT * FROM RegistroLavado WHERE RegistroID = :registroID")
    fun buscarRegistro(registroID: Long): RegistroLavado

    @Update
    fun actualizarRegistro(registro: RegistroLavado)

    @Query("DELETE FROM RegistroLavado WHERE RegistroID = :registroID")
    fun eliminarRegistro(registroID: Long)
}

