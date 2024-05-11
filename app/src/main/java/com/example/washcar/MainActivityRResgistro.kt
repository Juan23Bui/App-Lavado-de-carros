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
class MainActivityRResgistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_rresgistro)

        val btnVolverRR = findViewById<Button>(R.id.btnVolverRR)
        val btnMostrarRR = findViewById<Button>(R.id.btnMostrarRV)

        tableLayout = findViewById<TableLayout>(R.id.tableLayoutRegistro)

        //Conexion
        db = Room.databaseBuilder(application, LavadoBD::class.java, LavadoBD.DATABASE_NAME)
            .allowMainThreadQueries().build()

        btnMostrarRR.setOnClickListener {
            listarRegistros()
        }

        btnVolverRR.setOnClickListener {
            regresar()
        }
    }
    fun regresar() {
        val intent = Intent(this, MainActivityRegistro::class.java)
        startActivity(intent)
        finish()
    }
    private fun listarRegistros() {

        val registros = db.lavadoDao.obtenerRegistros()

        tableLayout.removeAllViews()

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

            tableLayout.addView(row)
        }
    }

}