package com.example.proyectoreservas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.lifecycle.lifecycleScope
import com.example.proyectoreservas.databinding.ActivityMainBinding
import com.example.proyectoreservas.db.UsuarioApplication
import com.example.proyectoreservas.fragments.RegistroFragment
import com.example.proyectoreservas.models.Usuario
import com.google.android.gms.ads.MobileAds
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var json = Json{prettyPrint=false}

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this) {}

        binding.register.setOnClickListener{
            openFragment()
        }

        binding.login.setOnClickListener{
            correctUser()
        }

        lifecycleScope.launch{
            UsuarioApplication.database.usuarioDao().addUser(Usuario(0,"Pepe","Algo","6666","pepe","1234",false))

            var lista = UsuarioApplication.database.usuarioDao().getAllUsers()
            lista.forEach{println(it)}
        }

    }


    /**
     * Abrir el fragmento para el registro del cliente.
     */
    private fun openFragment() {
        var fragmentManager = getSupportFragmentManager();
        var fragmentTransaction = fragmentManager.beginTransaction();
        var fragment1 = RegistroFragment();
        fragmentTransaction.add(android.R.id.content, fragment1).commit();
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
        var intent = Intent(this,UserHomeActivity::class.java).apply {
            putExtra("USUARIO",json.encodeToString(usuario))
        }
        startActivity(intent)
    }

}