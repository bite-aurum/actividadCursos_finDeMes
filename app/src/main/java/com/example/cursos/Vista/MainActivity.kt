package com.example.cursos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cursos.Adaptador.CursosAdaptador
import com.example.cursos.Modelo.dataCarreras
import com.example.cursos.Modelo.dataCursos
import com.example.cursos.Presentador.clsCatalogoCursosPresentador
import com.example.cursos.Vista.RegistroCursoActivity

class MainActivity : AppCompatActivity() {
    private lateinit var presenter: clsCatalogoCursosPresentador
    private lateinit var spinnerCarreras: Spinner
    private lateinit var recyclerViewCursos: RecyclerView
    private lateinit var adapter: CursosAdaptador
    private lateinit var btnRegistrarse: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        spinnerCarreras= findViewById(R.id.spnCategorias)
        recyclerViewCursos = findViewById(R.id.rvLista)
        recyclerViewCursos.layoutManager= GridLayoutManager(this, 2)
        adapter = CursosAdaptador(this, emptyList())
        recyclerViewCursos.adapter = adapter
        presenter = clsCatalogoCursosPresentador(this)
        configurarListeners()
        presenter.iniciar()

    }
    private fun configurarListeners() {
        spinnerCarreras.onItemSelectedListener =object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent:AdapterView<*>?, view: View?, position:Int,id:Long){
                presenter.seleccionarCarrera(position)
            }

            override fun onNothingSelected(parent:AdapterView<*>?) {}
        }
    }
    fun mostrarCarreras(carreras: List<dataCarreras>){
        val adapter= ArrayAdapter(this, android.R.layout.simple_spinner_item, carreras)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCarreras.adapter=adapter
    }
    fun mostrarCursos(cursos: List<dataCursos>) {
        adapter.actualizarLista(cursos)
    }

    fun mostrarDetalleCurso(curso: dataCursos) {
        Toast.makeText(this, "Seleccionaste: ${curso.nombre}", Toast.LENGTH_SHORT).show()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
    fun abrirRegistroCurso(curso: dataCursos) {
        val intent = Intent(this, RegistroCursoActivity::class.java).apply {
            putExtra("ID_CURSO", curso.id)
            putExtra("NOMBRE_CURSO", curso.nombre)
        }
        startActivity(intent)
    }
}