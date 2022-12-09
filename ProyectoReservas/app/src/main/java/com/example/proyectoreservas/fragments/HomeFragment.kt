package com.example.proyectoreservas.fragments

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoreservas.adapter.CitaAdapter
import com.example.proyectoreservas.databinding.FragmentHomeBinding
import com.example.proyectoreservas.db.UsuarioApplication
import com.example.proyectoreservas.models.Cita
import com.example.proyectoreservas.models.CitaListener
import com.example.proyectoreservas.models.Data
import kotlinx.coroutines.launch

/**
 * Fragmento home del usuario.
 */
class HomeFragment : Fragment(), CitaListener {
    private lateinit var binding: FragmentHomeBinding

  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Data.citaAdapter = CitaAdapter(mutableListOf(), this)

        var citas: MutableList<Cita>
        lifecycleScope.launch{
            citas = UsuarioApplication.database.citasDao().getCitaByClienteEmail(Data.usuarioActual?.email!!).toMutableList()
            Data.citaAdapter!!.setCitas(citas)
        }


        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this@HomeFragment.context)
            adapter= Data.citaAdapter
        }
    }


    /**
     * Accion al hacer click en un elemento de la lista
     */
    override fun onClick(item: Cita) {
        val builder = AlertDialog.Builder(this.requireContext())
            .setTitle("Datos Cita")
            .setMessage("Cliente: ${item.clienteEmail}\n" +
                    "Fecha: ${item.fecha}\n" +
                    "Hora: ${item.hora}\n" +
                    "Peluquera/o: ${item.peluquera}")

        builder.create().show()
    }


    /**
     * Acción al hacer un click lago en un elemento de la lista
     */
    override fun onLargeClick(item: Cita) {
        val builder = AlertDialog.Builder(this.requireContext())
            .setTitle("Confirmación de Borrado")
            .setMessage("Desea borrar la cita del día ${item.fecha} a las ${item.hora} ?")
            .setCancelable(false)
        builder.setPositiveButton("Si") { dialog, which ->
            lifecycleScope.launch {
                Data.citaAdapter!!.deleteData(item)
                UsuarioApplication.database.citasDao().deleteCita(item)
            }
            Toast.makeText(this.context, "Cita eliminada ", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") {dialog, which ->
            Toast.makeText(this.context, "Eliminacion cancelada", Toast.LENGTH_LONG).show()
        }

        builder.create().show()
    }


}