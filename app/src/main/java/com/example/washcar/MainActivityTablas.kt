package com.example.washcar

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.washcar.Config.LavadoBD

private lateinit var db: LavadoBD
private lateinit var tableLayoutC: TableLayout
private lateinit var tableLayoutV: TableLayout
private lateinit var tableLayoutS: TableLayout
private lateinit var tableLayoutR: TableLayout

class MainActivityTablas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_tablas)

        //tablas
        tableLayoutC = findViewById<TableLayout>(R.id.tableLayoutCliente)
        tableLayoutV = findViewById<TableLayout>(R.id.tableLayoutVehiculo)
        tableLayoutS = findViewById<TableLayout>(R.id.tableLayoutServicio)
        tableLayoutR = findViewById<TableLayout>(R.id.tableLayoutRegistro)

        val btnVolverT = findViewById<ImageButton>(R.id.btnVolverT)
        val btnRTablas = findViewById<ImageButton>(R.id.btnRTablas)

        //Conexion
        db = Room.databaseBuilder(application, LavadoBD::class.java, LavadoBD.DATABASE_NAME)
            .allowMainThreadQueries().build()

        btnVolverT.setOnClickListener {
            regresar()
        }

        btnRTablas.setOnClickListener {
            listarClientes()
            listarVehiculos()
            listarServicios()
            listarRegistros()
        }
    }

    fun regresar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun listarClientes() {
        // Obtener la lista de clientes de la base de datos
        val clientes = db.lavadoDao.obtenerCliente()

        tableLayoutC.removeAllViews()

        for (cliente in clientes) {
            val row = TableRow(this)

            val idTextView = TextView(this)
            idTextView.text = cliente.ClienteID.toString()
            idTextView.setPadding(8, 8, 8, 8)
            idTextView.setTextColor(Color.BLACK)

            val nombreTextView = TextView(this)
            nombreTextView.text = cliente.Nombre
            nombreTextView.setPadding(8, 8, 8, 8)
            nombreTextView.setTextColor(Color.BLACK)

            val apellidoTextView = TextView(this)
            apellidoTextView.text = cliente.Apellido
            apellidoTextView.setPadding(8, 8, 8, 8)
            apellidoTextView.setTextColor(Color.BLACK)

            val telefonoTextView = TextView(this)
            telefonoTextView.text = cliente.Telefono
            telefonoTextView.setPadding(8, 8, 8, 8)
            telefonoTextView.setTextColor(Color.BLACK)

            val emailTextView = TextView(this)
            emailTextView.text = cliente.Email
            emailTextView.setPadding(8, 8, 8, 8)
            emailTextView.setTextColor(Color.BLACK)

            val direccionTextView = TextView(this)
            direccionTextView.text = cliente.Direccion
            direccionTextView.setPadding(8, 8, 8, 8)
            direccionTextView.setTextColor(Color.BLACK)

            row.addView(idTextView)
            row.addView(nombreTextView)
            row.addView(apellidoTextView)
            row.addView(telefonoTextView)
            row.addView(emailTextView)
            row.addView(direccionTextView)

            tableLayoutC.addView(row)
        }
    }

    private fun listarVehiculos() {
        val vehiculos = db.lavadoDao.obtenerVehiculo()

        tableLayoutV.removeAllViews()

        for (vehiculo in vehiculos) {
            val row = TableRow(this)

            val idClienteTextView = TextView(this)
            idClienteTextView.text = vehiculo.ClienteID.toString()
            idClienteTextView.setPadding(8, 8, 8, 8)
            idClienteTextView.setTextColor(Color.BLACK)

            val idVehiculoTextView = TextView(this)
            idVehiculoTextView.text = vehiculo.VehiculoID.toString()
            idVehiculoTextView.setPadding(8, 8, 8, 8)
            idVehiculoTextView.setTextColor(Color.BLACK)

            val marcaTextView = TextView(this)
            marcaTextView.text = vehiculo.Marca
            marcaTextView.setPadding(8, 8, 8, 8)
            marcaTextView.setTextColor(Color.BLACK)

            val modeloTextView = TextView(this)
            modeloTextView.text = vehiculo.Modelo
            modeloTextView.setPadding(8, 8, 8, 8)
            modeloTextView.setTextColor(Color.BLACK)

            val placaTextView = TextView(this)
            placaTextView.text = vehiculo.Placa
            placaTextView.setPadding(8, 8, 8, 8)
            placaTextView.setTextColor(Color.BLACK)

            val colorTextView = TextView(this)
            colorTextView.text = vehiculo.Color
            colorTextView.setPadding(8, 8, 8, 8)
            colorTextView.setTextColor(Color.BLACK)

            val tipoTextView = TextView(this)
            tipoTextView.text = vehiculo.Tipo
            tipoTextView.setPadding(8, 8, 8, 8)
            tipoTextView.setTextColor(Color.BLACK)

            row.addView(idClienteTextView)
            row.addView(idVehiculoTextView)
            row.addView(marcaTextView)
            row.addView(modeloTextView)
            row.addView(placaTextView)
            row.addView(colorTextView)
            row.addView(tipoTextView)

            tableLayoutV.addView(row)
        }
    }

    private fun listarServicios() {
        val servicios = db.lavadoDao.obtenerServicio()

        tableLayoutS.removeAllViews()

        for (servicio in servicios) {
            val row = TableRow(this)

            val idServicioTextView = TextView(this)
            idServicioTextView.text = servicio.ServicioID.toString()
            idServicioTextView.setPadding(8, 8, 8, 8)
            idServicioTextView.setTextColor(Color.BLACK)

            val nombreServicioTextView = TextView(this)
            nombreServicioTextView.text = servicio.NombreS
            nombreServicioTextView.setPadding(8, 8, 8, 8)
            nombreServicioTextView.setTextColor(Color.BLACK)

            val precioTextView = TextView(this)
            precioTextView.text = servicio.Precio
            precioTextView.setPadding(8, 8, 8, 8)
            precioTextView.setTextColor(Color.BLACK)

            row.addView(idServicioTextView)
            row.addView(nombreServicioTextView)
            row.addView(precioTextView)

            tableLayoutS.addView(row)
        }
    }

    private fun listarRegistros() {

        val registros = db.lavadoDao.obtenerRegistros()

        tableLayoutR.removeAllViews()

        for (registro in registros) {
            val row = TableRow(this)

            val idRegistroTextView = TextView(this)
            idRegistroTextView.text = registro.RegistroID.toString()
            idRegistroTextView.setPadding(8, 8, 8, 8)
            idRegistroTextView.setTextColor(Color.BLACK)

            val idVehiculoTextView = TextView(this)
            idVehiculoTextView.text = registro.VehiculoID.toString()
            idVehiculoTextView.setPadding(8, 8, 8, 8)
            idVehiculoTextView.setTextColor(Color.BLACK)

            val idServicioTextView = TextView(this)
            idServicioTextView.text = registro.ServicioID.toString()
            idServicioTextView.setPadding(8, 8, 8, 8)
            idServicioTextView.setTextColor(Color.BLACK)

            val fechaLavadoTextView = TextView(this)
            fechaLavadoTextView.text = registro.FechaLavado
            fechaLavadoTextView.setPadding(8, 8, 8, 8)
            fechaLavadoTextView.setTextColor(Color.BLACK)

            val horaInicioTextView = TextView(this)
            horaInicioTextView.text = registro.HoraInicio
            horaInicioTextView.setPadding(8, 8, 8, 8)
            horaInicioTextView.setTextColor(Color.BLACK)

            val horaFinTextView = TextView(this)
            horaFinTextView.text = registro.HoraFin
            horaFinTextView.setPadding(8, 8, 8, 8)
            horaFinTextView.setTextColor(Color.BLACK)

            val precioTotalTextView = TextView(this)
            precioTotalTextView.text = registro.PrecioTotal
            precioTotalTextView.setPadding(8, 8, 8, 8)
            precioTotalTextView.setTextColor(Color.BLACK)

            row.addView(idRegistroTextView)
            row.addView(idVehiculoTextView)
            row.addView(idServicioTextView)
            row.addView(fechaLavadoTextView)
            row.addView(horaInicioTextView)
            row.addView(horaFinTextView)
            row.addView(precioTotalTextView)

            tableLayoutR.addView(row)
        }
    }
}