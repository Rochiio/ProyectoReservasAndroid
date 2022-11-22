package com.example.proyectoreservas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoreservas.CitaAdapter
import com.example.proyectoreservas.CitaClickListener
import com.example.proyectoreservas.databinding.FragmentHomeBinding
import com.example.proyectoreservas.db.UsuarioApplication
import com.example.proyectoreservas.models.Cita
import com.example.proyectoreservas.models.Data
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adaptador: CitaAdapter

  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var citas: MutableList<Cita> = mutableListOf()
        lifecycleScope.launch{
            citas = UsuarioApplication.database.citasDao().getCitaByClienteEmail(Data.usuarioActual?.email!!).toMutableList()
        }

        adaptador = CitaAdapter(citas)

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = adaptador
        }
    }



}