package com.example.washcar

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.washcar.Config.LavadoBD
import com.example.washcar.Models.Vehiculo

private lateinit var db: LavadoBD
class MainActivityVehiculo : AppCompatActivity() {

    //Vehiculo
    private lateinit var edtCodigoV: EditText
    private lateinit var edtMarca: EditText
    private lateinit var edtModelo: EditText
    private lateinit var edtPlaca: EditText
    private lateinit var edtColor: EditText
    private lateinit var edtTipo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_vehiculo)

        //Cliente
        val listIdCliente = findViewById<Spinner>(R.id.lstIdCliente)

        //BotonesVehiculo
        val btnBuscarV = findViewById<ImageButton>(R.id.btnBuscarV)
        val btnEditarV = findViewById<ImageButton>(R.id.btnEditarV)
        val btnEliminarV = findViewById<ImageButton>(R.id.btnEliminarV)
        val btnVolverV = findViewById<ImageButton>(R.id.btnVolverV)
        val btnGuardarV = findViewById<ImageButton>(R.id.btnGuardarV)
        val btnRegistroV = findViewById<Button>(R.id.btnRegistroV)

        //EditTextVehiculo
        edtCodigoV = findViewById(R.id.edtCodigoV)
        edtMarca = findViewById(R.id.edtMarca)
        edtModelo = findViewById(R.id.edtModelo)
        edtPlaca = findViewById(R.id.edtPlaca)
        edtColor = findViewById(R.id.edtColor)
        edtTipo = findViewById(R.id.edtTipo)

        //Conexion
        db = Room.databaseBuilder(application, LavadoBD::class.java, LavadoBD.DATABASE_NAME)
            .allowMainThreadQueries().build()

        //IDCLiente
        val opcionesClientes = mutableListOf<String>()
        val datos = db.lavadoDao.obtenerCliente()

        for (cliente in datos) {
            cliente.ClienteID?.let {
                opcionesClientes.add("${it}-${cliente.Nombre}")
            }
        }

        val adaptadorClientes = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesClientes)
        adaptadorClientes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        listIdCliente.adapter = adaptadorClientes

        btnGuardarV.setOnClickListener {
            val vehiculoID = edtCodigoV.text.toString().toLongOrNull()
            val marca = edtMarca.text.toString()
            val modelo = edtModelo.text.toString()
            val placa = edtPlaca.text.toString()
            val color = edtColor.text.toString()
            val tipo = edtTipo.text.toString()
            val clienteID = listIdCliente.selectedItem.toString().split("-")[0].toLong()

            if (vehiculoID != null && marca.isNotEmpty() && modelo.isNotEmpty() && placa.isNotEmpty() && color.isNotEmpty() && tipo.isNotEmpty() && clienteID.toString().isNotEmpty()) {
                val vehiculo = Vehiculo(vehiculoID, clienteID, marca, modelo, placa, color, tipo)
                try {
                    db.lavadoDao.insertVehiculo(vehiculo)
                    Toast.makeText(this, "Vehículo registrado correctamente", Toast.LENGTH_LONG).show()
                } catch (ex: Exception) {
                    Toast.makeText(this, "El ID ya existe", Toast.LENGTH_LONG).show()
                }
                limpiarCampos()
            } else {
                Toast.makeText(this, "Campos vacíos", Toast.LENGTH_LONG).show()
            }
        }

        btnBuscarV.setOnClickListener {
            val vehiculoID = edtCodigoV.text.toString().toIntOrNull()
            if (vehiculoID != null) {
                val vehiculoEncontrado = db.lavadoDao.buscarVehiculo(vehiculoID)
                if (vehiculoEncontrado != null) {
                    edtMarca.setText(vehiculoEncontrado.Marca)
                    edtModelo.setText(vehiculoEncontrado.Modelo)
                    edtPlaca.setText(vehiculoEncontrado.Placa)
                    edtColor.setText(vehiculoEncontrado.Color)
                    edtTipo.setText(vehiculoEncontrado.Tipo)
                } else {
                    Toast.makeText(this, "Vehículo no encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese un ID de vehículo válido", Toast.LENGTH_SHORT).show()
            }
        }

        btnEditarV.setOnClickListener {
            val vehiculoID = edtCodigoV.text.toString().toLongOrNull()
            val marca = edtMarca.text.toString()
            val modelo = edtModelo.text.toString()
            val placa = edtPlaca.text.toString()
            val color = edtColor.text.toString()
            val tipo = edtTipo.text.toString()
            val clienteID = listIdCliente.selectedItem.toString().split("-")[0].toLong()

            if (vehiculoID != null && marca.isNotEmpty() && modelo.isNotEmpty() && placa.isNotEmpty() && color.isNotEmpty() && tipo.isNotEmpty()&& clienteID.toString().isNotEmpty()) {
                val vehiculo = Vehiculo(vehiculoID, clienteID, marca, modelo, placa, color, tipo)
                try {
                    db.lavadoDao.actualizarVehiculo(vehiculo)
                    limpiarCampos()
                    Toast.makeText(this, "Vehículo actualizado correctamente", Toast.LENGTH_SHORT).show()
                } catch (ex: Exception) {
                    Toast.makeText(this, "Error al actualizar el vehículo", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnEliminarV.setOnClickListener {
            val vehiculoID = edtCodigoV.text.toString().toLongOrNull()
            if (vehiculoID != null) {
                try {
                    db.lavadoDao.eliminarVehiculo(vehiculoID)
                    Toast.makeText(this, "Vehículo eliminado correctamente", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                } catch (ex: Exception) {
                    Toast.makeText(this, "Error al eliminar el vehículo", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese un ID de vehículo válido", Toast.LENGTH_SHORT).show()
            }
        }

        btnVolverV.setOnClickListener {
            regresar()
        }
        btnRegistroV.setOnClickListener {
            cambiarRV()
        }
    }
    fun regresar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun cambiarRV(){
        val intent = Intent (this, MainActivityRVehiculos::class.java)
        startActivity(intent)
        finish()
    }
    private fun limpiarCampos() {
        edtCodigoV.text.clear()
        edtMarca.text.clear()
        edtModelo.text.clear()
        edtPlaca.text.clear()
        edtColor.text.clear()
        edtTipo.text.clear()
    }
}