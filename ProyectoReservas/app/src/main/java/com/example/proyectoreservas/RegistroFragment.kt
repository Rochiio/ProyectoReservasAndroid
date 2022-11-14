package com.example.proyectoreservas

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.proyectoreservas.databinding.FragmentRegistroBinding
import com.example.proyectoreservas.db.UsuarioApplication
import com.example.proyectoreservas.models.Usuario
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


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
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true) //poner fecha en la actionBar para volver

        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_registro, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_save -> {
                val user = Usuario(name = binding.editName.text.toString().trim(),
                    surname = binding.editSurname.text.toString().trim(),
                    telephone = binding.editPhone.text.toString().trim(),
                    email = binding.editEmail.text.toString().trim(),
                    password = binding.editPassword.toString().trim(),
                    admin = false
                )

                //lanzamos una corrutina
                lifecycleScope.launch {
                    //StoreApplication.database.storeDao().addStore(store)
                    UsuarioApplication.database.usuarioDao().addUser(user) //a la hora de guarda tenga ya id

                    Snackbar.make(binding.root, "Usuario agregado correctamente",
                        Snackbar.LENGTH_SHORT)
                        .show()
                    mActivity?.onBackPressed()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

        //return super.onOptionsItemSelected(item)
    }
}