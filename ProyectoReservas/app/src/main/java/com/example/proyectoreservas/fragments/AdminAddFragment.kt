package com.example.proyectoreservas.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.proyectoreservas.R
import com.example.proyectoreservas.databinding.FragmentAddBinding
import com.example.proyectoreservas.databinding.FragmentAdminAddBinding
import com.example.proyectoreservas.db.UsuarioApplication
import com.example.proyectoreservas.models.Cita
import com.example.proyectoreservas.models.Data
import com.example.proyectoreservas.models.Peluquera
import kotlinx.coroutines.launch
import java.util.*


class AdminAddFragment : Fragment() {
    private lateinit var binding: FragmentAdminAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdminAddBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonAdd.setOnClickListener {
            accionAdd()
        }

    }


    /**
     * Accion de añadir un/a peluquer@
     */
    private fun accionAdd() {
        val contexto = this.context

        if (binding.editName.text.toString().isNotEmpty() && binding.editEmail.text.toString().isNotEmpty() && binding.editPhone.text.toString().isNotEmpty()){

                lifecycleScope.launch {
                    val find = UsuarioApplication.database.peluquerasDao().findByEmail(binding.editEmail.text.toString())
                    find?.let {
                        Toast.makeText(contexto, "Ya existe un/a peluquer@ con este email ", Toast.LENGTH_LONG)
                            .show()
                    } ?: run {
                        val peluquera = Peluquera(
                            nombre = binding.editName.text.toString(),
                            correo = binding.editEmail.text.toString(),
                            telefono = binding.editPhone.text.toString()
                        )
                        UsuarioApplication.database.peluquerasDao().addPeluquera(peluquera)
                        Toast.makeText(context, "Peluquer@ añadida", Toast.LENGTH_LONG).show()
                        Data.peluqueraAdapter?.addData(peluquera)
                        clearFragment()
                    }
            }
        }else{
            Toast.makeText(this.context,"Deben de estar todos los campos rellenados",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    /**
     * Limpiar los apartados del fragmento
     */
    private fun clearFragment() {
        binding.editName.text?.clear()
        binding.editEmail.text?.clear()
        binding.editPhone.text?.clear()
    }


}