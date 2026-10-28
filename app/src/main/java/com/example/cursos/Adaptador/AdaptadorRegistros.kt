package com.example.cursos.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cursos.Modelo.dataCursos
import com.example.cursos.R
import com.example.cursos.Vista.clsVerRegistros

class AdaptadorRegistros(
    private var activity: clsVerRegistros,
    private var listaCursos: List<dataCursos>
) : RecyclerView.Adapter<AdaptadorRegistros.CursoViewHolder>() {

    class CursoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pbInscritos: ProgressBar = itemView.findViewById(R.id.progressBarInscritos)
        val tvNombreCurso: TextView = itemView.findViewById(R.id.tvNombreCurso)
        val tvInscritos: TextView = itemView.findViewById(R.id.tvInscritos)
        val tvTotal: TextView = itemView.findViewById(R.id.tvTotal)
        val imgCurso: ImageView = itemView.findViewById(R.id.imgCurso)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CursoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_registro, parent, false)
        return CursoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        val curso = listaCursos[position]

        holder.tvNombreCurso.text = curso.nombre.uppercase()
        holder.tvInscritos.text = "${curso.inscritos} inscritos"
        holder.tvTotal.text = "/ ${curso.cupoTotal} total"

        holder.pbInscritos.max = curso.cupoTotal.coerceAtLeast(1)
        holder.pbInscritos.progress = curso.inscritos.coerceIn(0, holder.pbInscritos.max)

        if (!curso.imagen.isNullOrEmpty()) {
            Glide.with(holder.itemView.context)
                .load(curso.imagen)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.imgCurso)
        } else {
            holder.imgCurso.setImageResource(android.R.drawable.ic_menu_gallery)
        }
    }

    override fun getItemCount(): Int {
        return listaCursos.size
    }

    fun actualizarLista(nuevaLista: List<dataCursos>) {
        listaCursos = nuevaLista
        notifyDataSetChanged()
    }
}
