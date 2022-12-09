package com.example.proyectoreservas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.lifecycle.lifecycleScope
import com.example.proyectoreservas.databinding.ActivityMainBinding
import com.example.proyectoreservas.db.UsuarioApplication
import com.example.proyectoreservas.fragments.RegistroFragment
import com.example.proyectoreservas.models.Data
import com.example.proyectoreservas.models.Peluquera
import com.example.proyectoreservas.models.Usuario
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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


        /**
         * Datos iniciales app, instalar y eliminar la app cada vez para que esto funcione.
         */
        lifecycleScope.launch{
            UsuarioApplication.database.usuarioDao().addUser(Data.userInicial)
             }

        lifecycleScope.launch {
            UsuarioApplication.database.usuarioDao().addUser(Data.adminInicial)
        }

        lifecycleScope.launch {
            UsuarioApplication.database.peluquerasDao().addPeluquera(Data.peluqueraInicial)
        }

        lifecycleScope.launch {
            UsuarioApplication.database.peluquerasDao().addPeluquera(Data.peluqueraInicial2)
        }

    }


    /**
     * Abrir el fragmento para el registro del cliente.
     */
    private fun openFragment() {
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        var fragment1 = RegistroFragment()
        fragmentTransaction.add(android.R.id.content, fragment1).commit()
    }


    /**
     * Para ver si el cliente es correcto.
     */
    private fun correctUser() {
        var email = binding.editTextEmail.text.toString()
        println(email)
        var password = binding.editTextPassword.text.toString()

        lifecycleScope.launch{
            val find = UsuarioApplication.database.usuarioDao().getUserByEmail(email)
            find?.let {
                if (it.password==password){
                    Snackbar.make(binding.root,"Usuario correcto",Snackbar.LENGTH_LONG).show()
                    iniciarActivity(find)
                }else{
                    Snackbar.make(binding.root,"Usuario incorrecto",Snackbar.LENGTH_LONG).show()
                }
            }?:run{
                Snackbar.make(binding.root,"Usuario no encontrado",Snackbar.LENGTH_LONG).show()
            }
        }
    }


    /**
     * Iniciar la actividad
     */
    private fun iniciarActivity(usuario: Usuario) {
        Data.usuarioActual=usuario
        if(!usuario.admin){
            var intent = Intent(this,UserHomeActivity::class.java)
            startActivity(intent)
        }else{
            var intent = Intent(this,AdminHomeActivity::class.java)
            startActivity(intent)
        }

    }

}