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
import com.example.washcar.Models.RegistroLavado

private lateinit var db: LavadoBD
class MainActivityRegistro : AppCompatActivity() {

    //RegistroLavado
    private lateinit var edtCodigoR: EditText
    private lateinit var edtFecha: EditText
    private lateinit var edtHoraInicio: EditText
    private lateinit var edtHoraFin: EditText
    private lateinit var edtPrecioR: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_registro)

        //Vehiculo
        val lstIDVehiculo = findViewById<Spinner>(R.id.lstIDVehiculo)

        //Servicio
        val lstIDServicioS = findViewById<Spinner>(R.id.lstIDServicioS)

        //BotonesRegistro
        val btnBuscarR = findViewById<ImageButton>(R.id.btnBuscarR)
        val btnEditarR = findViewById<ImageButton>(R.id.btnEditarR)
        val btnEliminarR = findViewById<ImageButton>(R.id.btnEliminarR)
        val btnVolverR = findViewById<ImageButton>(R.id.btnVolverR)
        val btnGuardarR = findViewById<ImageButton>(R.id.btnGuardarR)
        val btnRegistroR = findViewById<Button>(R.id.btnRegistroR)

        //EditTextVehiculo
        edtCodigoR = findViewById(R.id.edtCodigoR)
        edtFecha = findViewById(R.id.edtFecha)
        edtHoraInicio = findViewById(R.id.edtHoraInicio)
        edtHoraFin = findViewById(R.id.edtHoraFin)
        edtPrecioR = findViewById(R.id.edtPrecioR)

        //Conexion
        db = Room.databaseBuilder(application, LavadoBD::class.java, LavadoBD.DATABASE_NAME)
            .allowMainThreadQueries().build()

        //vehiculoId
        val opcionesVehiculos = mutableListOf<String>()
        val vehiculos = db.lavadoDao.obtenerVehiculo()

        for (vehiculo in vehiculos) {
            vehiculo.VehiculoID?.let {
                opcionesVehiculos.add("${it}-${vehiculo.Marca}")
            }
        }

        val adaptadorVehiculos = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesVehiculos)
        adaptadorVehiculos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        lstIDVehiculo.adapter = adaptadorVehiculos

        // ServicioID
        val opcionesServicios = mutableListOf<String>()
        val servicios = db.lavadoDao.obtenerServicio()

        for (servicio in servicios) {
            servicio.ServicioID?.let {
                opcionesServicios.add("${it}-${servicio.NombreS}")
            }
        }

        val adaptadorServicios = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesServicios)
        adaptadorServicios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        lstIDServicioS.adapter = adaptadorServicios

        btnGuardarR.setOnClickListener {
            val vehiculoID = edtCodigoR.text.toString().toLongOrNull()
            val fechaLavado = edtFecha.text.toString()
            val horaInicio = edtHoraInicio.text.toString()
            val horaFin = edtHoraFin.text.toString()
            val precioTotal = edtPrecioR.text.toString()
            val vehiculoSeleccionado = lstIDVehiculo.selectedItem.toString().split("-")[0].toLongOrNull()
            val servicioSeleccionado = lstIDServicioS.selectedItem.toString().split("-")[0].toLongOrNull()

            if (vehiculoID != null && fechaLavado.isNotBlank() && horaInicio.isNotBlank() && horaFin.isNotBlank() && precioTotal.isNotBlank() && vehiculoSeleccionado != null && servicioSeleccionado != null) {
                val registro = RegistroLavado(
                    VehiculoID = vehiculoSeleccionado,
                    ServicioID = servicioSeleccionado,
                    FechaLavado = fechaLavado,
                    HoraInicio = horaInicio,
                    HoraFin = horaFin,
                    PrecioTotal = precioTotal
                )
                try {
                    db.lavadoDao.insertRegistro(registro)
                    Toast.makeText(this, "Registro de lavado guardado correctamente", Toast.LENGTH_LONG).show()
                } catch (ex: Exception) {
                    Toast.makeText(this, "Error al guardar el registro de lavado", Toast.LENGTH_LONG).show()
                }
                limpiarCampos()
            } else {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show()
            }
        }

        btnBuscarR.setOnClickListener {
            val registroID = edtCodigoR.text.toString().toLongOrNull()
            if (registroID != null) {
                val registroEncontrado = db.lavadoDao.obtenerRegistroPorId(registroID)
                if (registroEncontrado != null) {
                    edtFecha.setText(registroEncontrado.FechaLavado)
                    edtHoraInicio.setText(registroEncontrado.HoraInicio)
                    edtHoraFin.setText(registroEncontrado.HoraFin)
                    edtPrecioR.setText(registroEncontrado.PrecioTotal)
                } else {
                    Toast.makeText(this, "Registro no encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese un ID de registro válido", Toast.LENGTH_SHORT).show()
            }
        }

        btnEditarR.setOnClickListener {
            val registroID = edtCodigoR.text.toString().toLongOrNull()
            val fechaLavado = edtFecha.text.toString()
            val horaInicio = edtHoraInicio.text.toString()
            val horaFin = edtHoraFin.text.toString()
            val precioTotal = edtPrecioR.text.toString()

            if (registroID != null && fechaLavado.isNotBlank() && horaInicio.isNotBlank() && horaFin.isNotBlank() && precioTotal.isNotBlank()) {
                val registro = RegistroLavado(registroID, 0, 0, fechaLavado, horaInicio, horaFin, precioTotal)
                try {
                    db.lavadoDao.actualizarRegistro(registro)
                    limpiarCampos()
                    Toast.makeText(this, "Registro actualizado correctamente", Toast.LENGTH_SHORT).show()
                } catch (ex: Exception) {
                    Toast.makeText(this, "Error al actualizar el registro", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnEliminarR.setOnClickListener {
            val registroID = edtCodigoR.text.toString().toLongOrNull()
            if (registroID != null) {
                try {
                    db.lavadoDao.eliminarRegistro(registroID)
                    Toast.makeText(this, "Registro eliminado correctamente", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                } catch (ex: Exception) {
                    Toast.makeText(this, "Error al eliminar el registro", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese un ID de registro válido", Toast.LENGTH_SHORT).show()
            }
        }


        btnVolverR.setOnClickListener {
            regresar()
        }
        btnRegistroR.setOnClickListener {
            cambiarRR()
        }
    }
    fun regresar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun cambiarRR(){
        val intent = Intent (this, MainActivityRResgistro::class.java)
        startActivity(intent)
        finish()
    }
    private fun limpiarCampos() {
        edtCodigoR.text.clear()
        edtFecha.text.clear()
        edtHoraInicio.text.clear()
        edtHoraFin.text.clear()
        edtPrecioR.text.clear()
    }
}