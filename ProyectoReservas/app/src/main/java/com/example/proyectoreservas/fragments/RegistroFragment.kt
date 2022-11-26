package com.example.proyectoreservas.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.proyectoreservas.MainActivity
import com.example.proyectoreservas.R
import com.example.proyectoreservas.databinding.FragmentRegistroBinding
import com.example.proyectoreservas.db.UsuarioApplication
import com.example.proyectoreservas.models.Usuario
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

/**
 * Fragmento del registro del usuario.
 */
class RegistroFragment : Fragment() {
    private lateinit var binding: FragmentRegistroBinding
    private var mActivity: MainActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegistroBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mActivity = activity as? MainActivity
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)


        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_registro, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                salir()
                true
            }

            R.id.action_save -> {
                var user = filtrarDatos()
                user?.let {
                    lifecycleScope.launch {
                        UsuarioApplication.database.usuarioDao().addUser(user)

                        Snackbar.make(
                            binding.root, "Usuario agregado correctamente",
                            Snackbar.LENGTH_SHORT
                        )
                            .show()

                        salir()
                    }
                }?: run {
                        Snackbar.make(
                            binding.root, "Datos requeridos vaciós y/o incorrectos",
                            Snackbar.LENGTH_SHORT
                        )
                            .show()
                    }
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }



    /**
     * Salir del fragmento
     */
    private fun salir() {
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }


    /**
     * Antes de crear al nuevo cliente ver si estan todos los datos necesarios y que no existe ningun usuario con ese email.
     */
    private fun filtrarDatos(): Usuario? {
        var name = binding.editName.text.toString().trim()
        var surname = binding.editSurname.text.toString().trim()
        var telephone = binding.editPhone.text.toString().trim()
        var email = binding.editEmail.text.toString().trim()
        var password = binding.editPassword.text.toString()
        println(password)

        if(name.isNotEmpty() &&  email.matches(
                Regex("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}"))
            && password.isNotEmpty()){

            return Usuario(0,name,surname,telephone,email,password,false)
        }
        return null
    }



}