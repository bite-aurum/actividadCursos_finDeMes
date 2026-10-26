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
import com.example.cursos.Presentador.IDetalleCursoVista
import com.example.cursos.Presentador.clsDetalleCursoPresentador
import com.example.cursos.R

class DetalleCursoActivity : AppCompatActivity(), IDetalleCursoVista {

    private lateinit var presentador: clsDetalleCursoPresentador
    private lateinit var imgDetalleCurso: ImageView
    private lateinit var txtNombreCurso: TextView
    private lateinit var txtCodigoCurso: TextView
    private lateinit var txtDescripcionCurso: TextView
    private lateinit var txtEspacioCurso: TextView
    private lateinit var txtTotalHoras: TextView
    private lateinit var txtDiasHorario: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_curso)

        imgDetalleCurso = findViewById(R.id.imgDetalleCurso)
        txtNombreCurso = findViewById(R.id.txtNombreCurso)
        txtCodigoCurso = findViewById(R.id.txtCodigoCurso)
        txtDescripcionCurso = findViewById(R.id.txtDescripcionCurso)
        txtEspacioCurso = findViewById(R.id.txtEspacioCurso)
        txtTotalHoras = findViewById(R.id.txtTotalHoras)
        txtDiasHorario = findViewById(R.id.txtDiasHorario)

        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }

        presentador = clsDetalleCursoPresentador(this)

        // El ID lo manda la pantalla del Catálogo con putExtra
        val idCurso = intent.getIntExtra("id_curso", -1)
        if (idCurso != -1) presentador.cargarDetalle(idCurso)
        else mostrarError("Curso no válido")
    }

    override fun mostrarDetalle(curso: dataCursoDetalle) {
        txtNombreCurso.text = curso.nombre
        txtCodigoCurso.text = "Código: ${curso.codigo}"

        txtDescripcionCurso.text =
            getString(R.string.txt_descripcion_curso) + "\n" + curso.descripcion

        txtEspacioCurso.text =
            getString(R.string.txt_espacio_curso) + "\n" + "${curso.edificio}, Salón ${curso.salon}"

        txtTotalHoras.text =
            getString(R.string.txt_total_horas) + "\n" + "${curso.totalHoras} Horas"

        // "10:00:00" -> "10:00"
        val inicio = curso.horaInicio.take(5)
        val fin = curso.horaFin.take(5)
        txtDiasHorario.text =
            getString(R.string.txt_dias_horario) + "\n" + "${curso.diasAtencion}\n($inicio - $fin hrs)"

        if (curso.imagen.isNotEmpty()) {
            Glide.with(this).load(curso.imagen).into(imgDetalleCurso)
        }
    }

    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}