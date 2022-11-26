package com.example.proyectoreservas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.proyectoreservas.databinding.CitasItemBinding
import com.example.proyectoreservas.models.Cita

class CitaAdapter(
    private var lista:MutableList<Cita>,
    //private var listener: CitaClickListener
    ) : RecyclerView.Adapter<CitaAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = CitasItemBinding.bind(view)

        fun bind(element: Cita){
            binding.textoPeluqueria.text=element.peluquera
        }

        fun setListener(cita: Cita) {
//            binding.root.setOnClickListener{ listener.onClick(cita) }
            }

        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.citas_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var cita = lista[position]
        holder.bind(cita)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun setCitas(citas: List<Cita>) {
        lista=citas.toMutableList()
    }


}