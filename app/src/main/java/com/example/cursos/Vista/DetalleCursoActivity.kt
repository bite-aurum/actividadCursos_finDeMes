package com.example.cursos.Vista

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.cursos.Modelo.dataCursoDetalle
import com.example.cursos.Presentador.clsDetalleCursoPresentador
import com.example.cursos.R

class DetalleCursoActivity : AppCompatActivity(){

    private lateinit var presentador: clsDetalleCursoPresentador
    private lateinit var imgDetalleCurso: ImageView
    private lateinit var txtNombreCurso: TextView
    private lateinit var txtCodigoCurso: TextView
    private lateinit var txtDescripcionCurso: TextView
    private lateinit var txtEspacioCurso: TextView
    private lateinit var txtTotalHoras: TextView
    private lateinit var txtDiasHorario: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_curso)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imgDetalleCurso = findViewById(R.id.imgDetalleCurso)
        txtNombreCurso = findViewById(R.id.txtNombreCurso)
        txtCodigoCurso = findViewById(R.id.txtCodigoCurso)
        txtDescripcionCurso = findViewById(R.id.txtDescripcionCurso)
        txtEspacioCurso = findViewById(R.id.txtEspacioCurso)
        txtTotalHoras = findViewById(R.id.txtTotalHoras)
        txtDiasHorario = findViewById(R.id.txtDiasHorario)

        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }

        val id = intent.getIntExtra("ID_CURSO", 0)
        val nombre = intent.getStringExtra("NOMBRE_CURSO") ?: ""
        val codigo = intent.getStringExtra("CODIGO_CURSO") ?: ""
        val imagen = intent.getStringExtra("IMAGEN_CURSO") ?: ""

        txtNombreCurso.text = nombre
        txtCodigoCurso.text = "Código: " + codigo

        if (imagen.isNotEmpty())
        {
            Glide.with(this).load(imagen).into(imgDetalleCurso)
        }
        presentador = clsDetalleCursoPresentador(this)

        if (id > 0)
        {
            presentador.cargarDetalle(id)
        } else {
            mostrarError("Curso no válido")
        }
    }

    fun mostrarDetalle(curse: dataCursoDetalle) {
        txtDescripcionCurso.text = "${getString(R.string.txt_descripcion_curso)}\n${curse.descripcion}"
        txtEspacioCurso.text = "${getString(R.string.txt_espacio_curso)}\n${curse.edificio}, Salón ${curse.salon}"
        txtTotalHoras.text = "${getString(R.string.txt_total_horas)}\n${curse.totalHoras} Horas"

        val inicio = if (curse.horaInicio.length >= 5) curse.horaInicio.substring(0, 5) else curse.horaInicio
        val fin = if (curse.horaFin.length >= 5) curse.horaFin.substring(0, 5) else curse.horaFin

        txtDiasHorario.text = "${getString(R.string.txt_dias_horario)}\n${curse.diasAtencion}\n($inicio - $fin hrs)"
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

}