package com.example.proyectoreservas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoreservas.R
import com.example.proyectoreservas.databinding.PeluquerasItemBinding
import com.example.proyectoreservas.models.Cita
import com.example.proyectoreservas.models.Peluquera

class PeluqueraAdapter(
    private var lista:MutableList<Peluquera>,
) : RecyclerView.Adapter<PeluqueraAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = PeluquerasItemBinding.bind(view)

        fun bind(element: Peluquera) {
            binding.editPeluName.text = element.nombre
            binding.editPeluEmail.text = element.correo
            binding.editPeluPhone.text = element.telefono
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.peluqueras_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var peluquera = lista[position]
        holder.bind(peluquera)
    }

    override fun getItemCount(): Int {
        return lista.size
    }


    /**
     * Poner una lista de peluquer@s
     */
    fun setPeluqueras(peluqueras : List<Peluquera>) {
        lista = peluqueras.toMutableList()
    }


    /**
     * Añadir una peluquera
     */
    fun addData(pelu: Peluquera) {
        lista.add(pelu)
        notifyDataSetChanged()
    }
}