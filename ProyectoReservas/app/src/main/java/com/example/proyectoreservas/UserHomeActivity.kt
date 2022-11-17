package com.example.proyectoreservas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.LinearLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoreservas.databinding.ActivityUserHomeBinding
import com.example.proyectoreservas.db.UsuarioApplication
import com.example.proyectoreservas.models.Cita
import com.example.proyectoreservas.models.Usuario
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class UserHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserHomeBinding
    private lateinit var adaptador: CitaAdapter
    private lateinit var user: Usuario
    private var json = Json{prettyPrint=false}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adaptador = CitaAdapter(mutableListOf())

        //Usuario
        var line = intent.getStringExtra(EXTRA_MESSAGE).toString()
        user = Json.decodeFromString(line)


        acciones()

        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.recycler.apply {
            getCitas()

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@UserHomeActivity)
            adapter = adaptador
        }
    }


    private fun acciones() {
        binding.buttonAbout.setOnClickListener {

        }

        binding.buttonLogOut.setOnClickListener {

        }

        binding.buttonAddCita.setOnClickListener {
            var fragmentSupp = getSupportFragmentManager()
            var transaction = fragmentSupp.beginTransaction()
            //transaction.add()
        }
    }

    private fun getCitas() {
        lifecycleScope.launch{
            val citas = UsuarioApplication.database.citasDao().getCitaByClienteEmail(user.email)
            adaptador.setCitas(citas)
        }
    }


}