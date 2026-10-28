
package com.example.cursos.Vista

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cursos.Adaptador.AdaptadorRegistros
import com.example.cursos.Modelo.dataCarreras
import com.example.cursos.Modelo.dataCursos
import com.example.cursos.Presentador.clsRegistrosPresentador
import com.example.cursos.R
import com.example.cursos.Modelo.obtenerUrlImagen

class clsVerRegistros : AppCompatActivity() {

    private lateinit var presenter: clsRegistrosPresentador
    private lateinit var spinnerCarreras: Spinner
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdaptadorRegistros
    private lateinit var txtTotal: TextView
    private lateinit var btnVolverCatalogo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cls_ver_registros)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        spinnerCarreras = findViewById(R.id.spnCategorias)
        recyclerView = findViewById(R.id.recyclerView)
        txtTotal = findViewById(R.id.txtTotal)
        btnVolverCatalogo = findViewById(R.id.btnVolverCatalogo)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdaptadorRegistros(this, emptyList())
        recyclerView.adapter = adapter

        presenter = clsRegistrosPresentador(this)

        configurarListeners()
        presenter.iniciar()
    }

    private fun configurarListeners() {
        spinnerCarreras.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    presenter.seleccionarCarrera(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        btnVolverCatalogo.setOnClickListener {
            finish()
        }

        findViewById<com.google.android.material.appbar.MaterialToolbar>(
            R.id.tlb_estadoInscripciones
        ).setNavigationOnClickListener {
            finish()
        }
    }

    fun mostrarCarreras(carreras: List<dataCarreras>) {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            carreras
        )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinnerCarreras.adapter = adapter
    }

    fun mostrarRegistros(registros: List<dataCursos>) {
        adapter.actualizarLista(registros)
    }

    fun mostrarTotal(total: Int) {
        txtTotal.text = "Total: $total"
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}
