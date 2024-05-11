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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.washcar.Config.LavadoBD

private lateinit var db: LavadoBD
private lateinit var tableLayout: TableLayout
class MainActivityRVehiculos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_rvehiculos)

        val btnVolverRV = findViewById<Button>(R.id.btnVolverRV)
        val btnMostrarRV = findViewById<Button>(R.id.btnMostrarrV)

        tableLayout = findViewById<TableLayout>(R.id.tableLayoutVehiculo)

        //Conexion
        db = Room.databaseBuilder(application, LavadoBD::class.java, LavadoBD.DATABASE_NAME)
            .allowMainThreadQueries().build()

        btnMostrarRV.setOnClickListener {
            listarVehiculos()
        }

        btnVolverRV.setOnClickListener {
            regresar()
        }
    }
    private fun listarVehiculos() {
        val vehiculos = db.lavadoDao.obtenerVehiculo()

        tableLayout.removeAllViews()

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

            tableLayout.addView(row)
        }
    }
    fun regresar() {
        val intent = Intent(this, MainActivityVehiculo::class.java)
        startActivity(intent)
        finish()
    }
}