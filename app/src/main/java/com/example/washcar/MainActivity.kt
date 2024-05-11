package com.example.washcar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistroL);
        val btnServicios = findViewById<Button>(R.id.btnServicios);
        val btnVehiculos = findViewById<Button>(R.id.btnVehiculos);
        val btnClientes = findViewById<Button>(R.id.btnClientes);
        val btnTablas = findViewById<ImageButton>(R.id.btnTablas);

        btnRegistrar.setOnClickListener {
            cambiar1()
        }
        btnServicios.setOnClickListener {
            cambiar2()
        }
        btnVehiculos.setOnClickListener {
            cambiar3()
        }
        btnClientes.setOnClickListener {
            cambiar4()
        }
        btnTablas.setOnClickListener {
            cambiar5()
        }
    }
    fun cambiar1 (){
        val intent = Intent (this, MainActivityRegistro::class.java)
        startActivity(intent)
        finish()
    }
    fun cambiar2 (){
        val intent = Intent (this, MainActivityServicio::class.java)
        startActivity(intent)
        finish()
    }
    fun cambiar3 (){
        val intent = Intent (this, MainActivityVehiculo::class.java)
        startActivity(intent)
        finish()
    }
    fun cambiar4 (){
        val intent = Intent (this, MainActivityCliente::class.java)
        startActivity(intent)
        finish()
    }
    fun cambiar5 (){
        val intent = Intent ( this, MainActivityTablas::class.java)
        startActivity(intent)
        finish()
    }
}