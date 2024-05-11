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
import com.example.washcar.Models.Servicio

private lateinit var db: LavadoBD
class MainActivityServicio : AppCompatActivity() {

    //Servicio
    private lateinit var edtCodigoS: EditText
    private lateinit var lstServicio: Spinner
    private lateinit var edtPrecio: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_servicio)

        //BotonesRegistro
        val btnBuscarS = findViewById<ImageButton>(R.id.btnBuscarS)
        val btnEditarS = findViewById<ImageButton>(R.id.btnEditarS)
        val btnEliminarS = findViewById<ImageButton>(R.id.btnEliminarS)
        val btnVolverS = findViewById<ImageButton>(R.id.btnVolverS)
        val btnGuardarS = findViewById<ImageButton>(R.id.btnGuardarS)
        val btnRegistroS = findViewById<Button>(R.id.btnRegistroS)


        //Servicio
        edtCodigoS = findViewById(R.id.edtCodigoS)
        lstServicio = findViewById(R.id.lstServicioS)
        edtPrecio = findViewById(R.id.edtPrecioS)

        //Opciones de servicios
        val opcionesServicios = listOf("Lavado exterior", "Limpieza Interior", "Lavado de motor", "Encerado y pulido", "Servicios de mantenimiento", "Servicios de restauración")
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesServicios)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        lstServicio.adapter = adaptador

        //Conexion
        db = Room.databaseBuilder(application, LavadoBD::class.java, LavadoBD.DATABASE_NAME)
            .allowMainThreadQueries().build()

        btnGuardarS.setOnClickListener {
            val servicioID = edtCodigoS.text.toString().toLongOrNull()
            val servicio = lstServicio.selectedItem.toString()
            val precio = edtPrecio.text.toString()

            if (servicioID != null && servicio.isNotEmpty() && precio.isNotEmpty()) {
                val servicio = Servicio(servicioID, servicio, precio)
                try {
                    db.lavadoDao.insertServicio(servicio)
                    Toast.makeText(this, "Servicio registrado correctamente", Toast.LENGTH_SHORT).show()
                } catch (ex: Exception) {
                    Toast.makeText(this, "El ID ya existe", Toast.LENGTH_SHORT).show()
                }
                limpiarCampos()
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnBuscarS.setOnClickListener {
            val servicioID = edtCodigoS.text.toString().toIntOrNull()
            if (servicioID != null) {
                val servicioEncontrado = db.lavadoDao.buscarServicio(servicioID)
                if (servicioEncontrado != null) {
                    lstServicio.setSelection(opcionesServicios.indexOf(servicioEncontrado.NombreS))
                    edtPrecio.setText(servicioEncontrado.Precio.toString())
                } else {
                    Toast.makeText(this, "Servicio no encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese un ID de servicio válido", Toast.LENGTH_SHORT).show()
            }
        }

        btnEditarS.setOnClickListener {
            val servicioID = edtCodigoS.text.toString().toLongOrNull()
            val servicio = lstServicio.selectedItem.toString()
            val precio = edtPrecio.text.toString()

            if (servicioID != null && servicio.isNotEmpty() && precio.isNotEmpty()) {
                val servicioActualizado = Servicio(servicioID, servicio, precio)
                try {
                    db.lavadoDao.actualizarServicio(servicioActualizado)
                    Toast.makeText(this, "Servicio actualizado correctamente", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                } catch (ex: Exception) {
                    Toast.makeText(this, "Error al actualizar el servicio", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnEliminarS.setOnClickListener {
            val servicioID = edtCodigoS.text.toString().toIntOrNull()
            if (servicioID != null) {
                try {
                    db.lavadoDao.eliminarServicio(servicioID)
                    Toast.makeText(this, "Servicio eliminado correctamente", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                } catch (ex: Exception) {
                    Toast.makeText(this, "Error al eliminar el servicio", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese un ID de servicio válido", Toast.LENGTH_SHORT).show()
            }
        }
        btnVolverS.setOnClickListener {
            regresar()
        }
        btnRegistroS.setOnClickListener {
            cambiarRS()
        }
    }
    fun regresar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun cambiarRS(){
        val intent = Intent (this, MainActivityRServicio::class.java)
        startActivity(intent)
        finish()
    }
    private fun limpiarCampos() {
        edtCodigoS.text.clear()
        edtPrecio.text.clear()
    }

}