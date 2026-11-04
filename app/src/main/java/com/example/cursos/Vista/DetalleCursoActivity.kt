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
    private lateinit var btnBack: Button

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
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener { finish() }

        val id = intent.getIntExtra("ID_CURSO", 0)
        val nombre = intent.getStringExtra("NOMBRE_CURSO") ?: ""
        val codigo = intent.getStringExtra("CODIGO_CURSO") ?: ""
        val descripcion = intent.getStringExtra("DESCRIPCION_CURSO") ?: ""
        val edificio = intent.getStringExtra("EDIFICIO_CURSO") ?: ""
        val salon = intent.getStringExtra("SALON_CURSO") ?: ""
        val totalHoras = intent.getIntExtra("TOTAL_HORAS_CURSO", 0)
        val dias = intent.getStringExtra("DIAS_ATENCION_CURSO") ?: ""
        val horaInicio = intent.getStringExtra("HORA_INICIO_CURSO") ?: ""
        val horaFin = intent.getStringExtra("HORA_FIN_CURSO") ?: ""

        txtNombreCurso.text = nombre
        txtCodigoCurso.text = "Código: " + codigo
        txtDescripcionCurso.text = getString(R.string.txt_descripcion_curso) + "\n" + descripcion
        txtEspacioCurso.text = getString(R.string.txt_espacio_curso) + "\n" + edificio + ", Salón " + salon
        txtTotalHoras.text = getString(R.string.txt_total_horas) + "\n" + totalHoras + " Horas"
        txtDiasHorario.text = getString(R.string.txt_dias_horario) + "\n" + dias +
                "\n(" + horaInicio.take(5) + " - " + horaFin.take(5) + " hrs)"

        presentador = clsDetalleCursoPresentador(this)

        if (id != 0) {
            presentador.cargarDetalle(id)
        }
    }

    fun mostrarImagen(url: String) {
        if (url.isNotEmpty()) {
            Glide.with(this).load(url).into(imgDetalleCurso)
        }
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

}