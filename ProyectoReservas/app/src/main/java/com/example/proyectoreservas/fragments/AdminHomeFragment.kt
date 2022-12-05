package com.example.proyectoreservas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoreservas.R
import com.example.proyectoreservas.adapter.CitaAdapter
import com.example.proyectoreservas.adapter.PeluqueraAdapter
import com.example.proyectoreservas.databinding.FragmentAdminHomeBinding
import com.example.proyectoreservas.databinding.FragmentHomeBinding
import com.example.proyectoreservas.db.UsuarioApplication
import com.example.proyectoreservas.models.Cita
import com.example.proyectoreservas.models.Data
import com.example.proyectoreservas.models.Peluquera
import kotlinx.coroutines.launch


class AdminHomeFragment : Fragment() {

    private lateinit var binding: FragmentAdminHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdminHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Data.peluqueraAdapter = PeluqueraAdapter(mutableListOf())

        var peluqueros: List<Peluquera>
        lifecycleScope.launch{
            peluqueros = UsuarioApplication.database.peluquerasDao().getAllPeluqueras().toList()
            Data.peluqueraAdapter!!.setPeluqueras(peluqueros)
        }


        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this@AdminHomeFragment.context)
            adapter= Data.peluqueraAdapter
        }
    }


}