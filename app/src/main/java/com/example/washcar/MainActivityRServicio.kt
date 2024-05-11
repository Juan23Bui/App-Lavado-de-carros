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
class MainActivityRServicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_rservicio)

        val btnVolverRS = findViewById<Button>(R.id.btnVolverRS)
        val btnMostrarRS = findViewById<Button>(R.id.btnMostrarRS)

        tableLayout = findViewById<TableLayout>(R.id.tableLayoutServicio)

        //Conexion
        db = Room.databaseBuilder(application, LavadoBD::class.java, LavadoBD.DATABASE_NAME)
            .allowMainThreadQueries().build()

        btnMostrarRS.setOnClickListener {
            listarServicios()
        }

        btnVolverRS.setOnClickListener {
            regresar()
        }
    }

    private fun listarServicios() {
        val servicios = db.lavadoDao.obtenerServicio()

        tableLayout.removeAllViews()

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

            tableLayout.addView(row)
        }
    }


    fun regresar() {
        val intent = Intent(this, MainActivityServicio::class.java)
        startActivity(intent)
        finish()
    }
}