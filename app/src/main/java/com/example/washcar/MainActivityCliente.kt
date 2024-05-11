package com.example.washcar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.washcar.Config.LavadoBD
import com.example.washcar.Models.Cliente

private lateinit var db: LavadoBD
class MainActivityCliente : AppCompatActivity() {

    //Cliente
    private lateinit var edtCodigoC: EditText
    private lateinit var edtNombreC: EditText
    private lateinit var edtApellidoC: EditText
    private lateinit var edtTelefono: EditText
    private lateinit var edtCorreo: EditText
    private lateinit var edtDireccion: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_cliente)

        //BotonesCliente
        val btnBuscarC = findViewById<ImageButton>(R.id.btnBuscarC)
        val btnEditarC = findViewById<ImageButton>(R.id.btnEditarC)
        val btnEliminarC = findViewById<ImageButton>(R.id.btnEliminarC)
        val btnVolverC = findViewById<ImageButton>(R.id.btnVolverC)
        val btnGuardarC = findViewById<ImageButton>(R.id.btnGuardarC)
        val btnRegistroC = findViewById<Button>(R.id.btnRegistroC)

        //EditTextClientes
        edtCodigoC = findViewById(R.id.edtCodigoC)
        edtNombreC = findViewById(R.id.edtNombreC)
        edtApellidoC = findViewById(R.id.edtApellidoC)
        edtTelefono = findViewById(R.id.edtTelefono)
        edtCorreo = findViewById(R.id.edtCorreo)
        edtDireccion = findViewById(R.id.edtDireccion)

        //Conexion
        db = Room.databaseBuilder(application, LavadoBD::class.java, LavadoBD.DATABASE_NAME)
            .allowMainThreadQueries().build()

        btnGuardarC.setOnClickListener {
            val clienteID = edtCodigoC.text.toString().toLongOrNull()
            val nombre = edtNombreC.text.toString()
            val apellido = edtApellidoC.text.toString()
            val telefono = edtTelefono.text.toString()
            val email = edtCorreo.text.toString()
            val direccion = edtDireccion.text.toString()

            if (clienteID != null && nombre.isNotEmpty() && apellido.isNotEmpty() && telefono.isNotEmpty() && email.isNotEmpty() && direccion.isNotEmpty()) {
                val cliente = Cliente(clienteID, nombre, apellido, telefono, email, direccion)
                try {
                    db.lavadoDao.insert(cliente)
                    Toast.makeText(this, "Cliente registrado correctamente", Toast.LENGTH_LONG).show()
                } catch (ex: Exception) {
                    Toast.makeText(this, "El ID ya existe", Toast.LENGTH_LONG).show()
                }
                limpiarCampos()
            } else {
                Toast.makeText(this, "Campos vacíos", Toast.LENGTH_LONG).show()
            }
        }


        btnBuscarC.setOnClickListener {
            val clienteID = edtCodigoC.text.toString().toIntOrNull()
            if (clienteID != null) {
                val clienteEncontrado = db.lavadoDao.buscarCliente(clienteID)
                if (clienteEncontrado != null) {
                    edtNombreC.setText(clienteEncontrado.Nombre)
                    edtApellidoC.setText(clienteEncontrado.Apellido)
                    edtTelefono.setText(clienteEncontrado.Telefono)
                    edtCorreo.setText(clienteEncontrado.Email)
                    edtDireccion.setText(clienteEncontrado.Direccion)
                } else {
                    Toast.makeText(this, "Cliente no encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese un ID de cliente válido", Toast.LENGTH_SHORT).show()
            }
        }

        btnEditarC.setOnClickListener {
            val clienteID = edtCodigoC.text.toString().toLongOrNull()
            val nombre = edtNombreC.text.toString()
            val apellido = edtApellidoC.text.toString()
            val telefono = edtTelefono.text.toString()
            val correo = edtCorreo.text.toString()
            val direccion = edtDireccion.text.toString()

            if (clienteID != null && nombre.isNotEmpty() && apellido.isNotEmpty() && telefono.isNotEmpty() && correo.isNotEmpty() && direccion.isNotEmpty()) {
                val cliente = Cliente(clienteID, nombre, apellido, telefono, correo, direccion)
                try {
                    db.lavadoDao.actualizarCliente(cliente)
                    limpiarCampos()
                    Toast.makeText(this, "Cliente actualizado correctamente", Toast.LENGTH_SHORT).show()
                } catch (ex: Exception) {
                    Toast.makeText(this, "Error al actualizar el cliente", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnEliminarC.setOnClickListener {
            val clienteID = edtCodigoC.text.toString().toLongOrNull()
            if (clienteID != null) {
                try {
                    db.lavadoDao.eliminarCliente(clienteID)
                    Toast.makeText(this, "Cliente eliminado correctamente", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                } catch (ex: Exception) {
                    Toast.makeText(this, "Error al eliminar el cliente", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese un ID de cliente válido", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegistroC.setOnClickListener {
            cambiarRC()
        }
        btnVolverC.setOnClickListener {
            regresar()
        }
    }
    fun regresar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun cambiarRC(){
        val intent = Intent (this, MainActivityRClientes::class.java)
        startActivity(intent)
        finish()
    }
    private fun limpiarCampos() {
        edtCodigoC.text.clear()
        edtNombreC.text.clear()
        edtApellidoC.text.clear()
        edtTelefono.text.clear()
        edtCorreo.text.clear()
        edtDireccion.text.clear()
    }
}