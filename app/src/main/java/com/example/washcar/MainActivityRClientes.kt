package com.example.washcar

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.washcar.Config.LavadoBD

private lateinit var db: LavadoBD
private lateinit var tableLayout: TableLayout
class MainActivityRClientes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_rclientes)

        val btnVolverRC = findViewById<Button>(R.id.btnVolverRC)
        val btnMostrarRC = findViewById<Button>(R.id.btnMostrarRC)

        tableLayout = findViewById<TableLayout>(R.id.tableLayoutCliente)

        //Conexion
        db = Room.databaseBuilder(application, LavadoBD::class.java, LavadoBD.DATABASE_NAME)
            .allowMainThreadQueries().build()

        btnMostrarRC.setOnClickListener {
            listarClientes()
        }

        btnVolverRC.setOnClickListener {
            regresar()
        }
    }
    fun regresar() {
        val intent = Intent(this, MainActivityCliente::class.java)
        startActivity(intent)
        finish()
    }

    private fun listarClientes() {
        // Obtener la lista de clientes de la base de datos
        val clientes = db.lavadoDao.obtenerCliente()

        tableLayout.removeAllViews()

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

            tableLayout.addView(row)
        }
    }
}