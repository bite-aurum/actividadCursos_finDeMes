package com.example.cursos.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cursos.MainActivity
import com.example.cursos.Modelo.dataCursos
import com.example.cursos.R
import android.widget.Button
import com.example.cursos.Modelo.obtenerUrlImagen

class CursosAdaptador(
    private var activity: MainActivity,
    private var listaCursos: List<dataCursos>
) : RecyclerView.Adapter<CursosAdaptador.CursoViewHolder>() {

    class CursoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombreCurso: TextView = itemView.findViewById(R.id.tvNombreCurso)
        val tvSalon: TextView = itemView.findViewById(R.id.tvSalon)
        val imgCurso: ImageView = itemView.findViewById(R.id.imgCurso)
        val btnRegistrarse: Button = itemView.findViewById(R.id.btnRegistrarse)
        val btnVerDetalle: Button = itemView.findViewById(R.id.btnVerDetalle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_curso, parent, false)
        return CursoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        val curso = listaCursos[position]
        holder.tvNombreCurso.text = curso.nombre
        holder.tvSalon.text = "Salón: ${curso.salon}"
        if (!curso.imagen.isNullOrEmpty()) {
            Glide.with(holder.itemView.context)
                .load(obtenerUrlImagen(curso.imagen))
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.imgCurso)
        }

        holder.btnRegistrarse.setOnClickListener {
            activity.abrirRegistroCurso(curso)
        }

        holder.btnVerDetalle.setOnClickListener {
            activity.mostrarDetalleCurso(curso)
        }

        holder.itemView.setOnClickListener {
            activity.mostrarDetalleCurso(curso)
        }
    }

    override fun getItemCount(): Int = listaCursos.size

    fun actualizarLista(nuevaLista: List<dataCursos>) {
        listaCursos = nuevaLista
        notifyDataSetChanged()
    }
}