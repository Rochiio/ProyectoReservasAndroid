package com.example.proyectoreservas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.proyectoreservas.databinding.ActivityMainBinding
import com.example.proyectoreservas.db.UsuarioApplication
import com.example.proyectoreservas.models.Usuario
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.register.setOnClickListener{
            openFragment()
        }

        binding.login.setOnClickListener{
            correctUser()
        }

    }

    private fun openFragment() {
        val fragment = RegistroFragment() //crear el fragment

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.mainContainer, fragment) //ponemos el fragmento en
        fragmentTransaction.addToBackStack(null) //permite que se pueda volver atras
        fragmentTransaction.commit()

    }

    private fun correctUser() {
        lifecycleScope.launch{
            var email = binding.labelEmail.text.toString()
            var password = binding.labelPassword.text.toString()

            val find = UsuarioApplication.database.usuarioDao().getAllUsers()
            var encontrado = find.firstOrNull { it.email == email && it.password == password }

            encontrado?.let {
                if (it.password==password){

                }else{
                    Snackbar.make(binding.root,"Usuario incorrecto",Snackbar.LENGTH_LONG).show()
                }
            }?:run{
                Snackbar.make(binding.root,"Usuario no encontrado",Snackbar.LENGTH_LONG).show()
            }
        }
    }

}