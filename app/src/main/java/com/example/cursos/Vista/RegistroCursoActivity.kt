package com.example.cursos.Vista

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.cursos.Modelo.obtenerUrlImagen
import com.example.cursos.Presentador.clsRegistroPresentador
import com.example.cursos.R
import java.util.Calendar

class RegistroCursoActivity : AppCompatActivity() {
    private lateinit var presenter: clsRegistroPresentador

    private lateinit var imgCurso: ImageView
    private lateinit var txtNombreCurso: TextView
    private lateinit var edtMatricula: EditText
    private lateinit var edtFecha: EditText
    private lateinit var edtCorreo: EditText
    private lateinit var btnConfirmar: Button
    private lateinit var btnCancelar: Button

    // Variable para almacenar el ID del curso recibido
    private var cursoId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_curso)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imgCurso = findViewById(R.id.imgRegistroCurso)
        txtNombreCurso = findViewById(R.id.txtRegistroCurso)
        edtMatricula = findViewById(R.id.edtMatricula)
        edtFecha = findViewById(R.id.edtFecha)
        edtCorreo = findViewById(R.id.edtCorreo)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        btnCancelar = findViewById(R.id.btnCancelar)

        // Inicializar Presentador
        presenter = clsRegistroPresentador(this)

        cursoId = intent.getIntExtra("CURSO_ID", 0)
        val nombreCurso = intent.getStringExtra("NOMBRE_CURSO") ?: "CURSO SELECCIONADO"
        val urlImagen = intent.getStringExtra("IMAGEN_CURSO") ?: ""

        txtNombreCurso.text = nombreCurso
        mostrarImagenCurso(urlImagen)

        btnConfirmar.setOnClickListener(this::ejecutarConfirmar)
        btnCancelar.setOnClickListener { finish() }
        edtFecha.setOnClickListener { mostrarDatePicker() }
    }

    fun ejecutarConfirmar(v: View) {
        presenter.confirmarInscripcion(
            cursoId,
            edtMatricula.text.toString().trim(),
            edtFecha.text.toString().trim(),
            edtCorreo.text.toString().trim()
        )
    }

    fun inscripcionExitosa(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        finish()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    fun mostrarImagenCurso(urlImagen: String) {
        Glide.with(this)
            .load(obtenerUrlImagen(urlImagen))
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(imgCurso)
    }

    private fun mostrarDatePicker() {
        val calendario = Calendar.getInstance()
        val anio = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                val fechaSeleccionada = String.format("%04d-%02d-%02d", year, monthOfYear + 1, dayOfMonth)
                edtFecha.setText(fechaSeleccionada)
            },
            anio,
            mes,
            dia
        )
        datePicker.show()
    }
}
