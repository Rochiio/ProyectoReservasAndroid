package com.example.proyectoreservas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoreservas.R
import com.example.proyectoreservas.databinding.CitasItemBinding
import com.example.proyectoreservas.models.Cita
import com.example.proyectoreservas.models.CitaListener

class CitaAdapter(
    private var lista:MutableList<Cita>,
    private var listener: CitaListener
    ) : RecyclerView.Adapter<CitaAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = CitasItemBinding.bind(view)

        fun bind(element: Cita, listener: CitaListener) {
            binding.textoPeluqueria.text = element.fecha
            binding.photo.setImageResource(R.drawable.pelu)
            binding.photo.setOnClickListener {
                listener.onClick(element)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.citas_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var cita = lista[position]
        holder.bind(cita, listener)
    }

    override fun getItemCount(): Int {
        return lista.size
    }



    /**
     * Poner una cita
     */
    fun setCitas(citas: List<Cita>) {
        lista=citas.toMutableList()
    }

    /**
     * Añadir una cita
     */
    fun addData(cita: Cita) {
        lista.add(cita)
        notifyDataSetChanged()
    }


}