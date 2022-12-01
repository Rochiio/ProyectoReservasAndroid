package com.example.proyectoreservas.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.lifecycleScope
import com.example.proyectoreservas.adapter.CitaAdapter
import com.example.proyectoreservas.R
import com.example.proyectoreservas.databinding.FragmentAddBinding
import com.example.proyectoreservas.db.UsuarioApplication
import com.example.proyectoreservas.models.Cita
import com.example.proyectoreservas.models.Data
import com.example.proyectoreservas.models.Peluquera
import kotlinx.coroutines.launch
import java.util.*

/**
 * Fragmento de añadir citas.
 */
class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var adaptador: CitaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var peluqueras:List<Peluquera> = listOf()
        lifecycleScope.launch {
            peluqueras = UsuarioApplication.database.peluquerasDao().getAllPeluqueras()
        }
        val arrayPAdapter = this.context?.let { ArrayAdapter(it, R.layout.listapeluqueras_item , peluqueras) }
        binding.editPeluquera.setAdapter(arrayPAdapter)

        val horas = resources.getStringArray(R.array.horas)
        val arrayHAdapter = this.context?.let { ArrayAdapter(it,  R.layout.listahoras_item , horas) }
        binding.editTime.setAdapter(arrayHAdapter)


        binding.buttonAdd.setOnClickListener {
            val dia = binding.editDay.text.toString()
            val hora = binding.editTime.text.toString()
            val peluquera = binding.editTime.text.toString()

            accionAdd(dia, hora, peluquera)
        }

        binding.editDay.setOnClickListener{
            val c = Calendar.getInstance()

            // Saber el dia de hoy
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this.requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.editDay.setText(dat)
                },
                year, month, day)

            datePickerDialog.show()
        }

    }


    /**
     * Accion de añadir una cita, filtra si el año es este y el dia no es anterior a hoy, ni hoy.
     * @param dia dia de la cita.
     * @param hora hora de la cita.
     * @param peluquera que se quiere para la cita.
     */
    private fun accionAdd(dia: String, hora: String, peluquera: String) {
        val contexto = this.context
        val camposFecha = dia.split("-")

        if (dia.isNotEmpty() && hora.isNotEmpty() && peluquera.isNotEmpty()){
            if (camposFecha[0].toInt() > Data.day && camposFecha[1].toInt() >= Data.month && camposFecha[1].toInt()== Data.year ) {

                lifecycleScope.launch {
                    val find = UsuarioApplication.database.citasDao()
                        .peluqueraHoraDiaTieneCita(hora, dia, peluquera)
                    find?.let {
                        Toast.makeText(contexto, "$peluquera ya tiene una cita ", LENGTH_LONG)
                            .show()
                    } ?: run {
                        val cita = Cita(
                            clienteEmail = Data.usuarioActual?.email!!,
                            fecha = dia,
                            hora = hora,
                            peluquera = peluquera
                        )
                        UsuarioApplication.database.citasDao().addCita(cita)
                        Toast.makeText(context, "Cita añadida", LENGTH_LONG).show()
                        Data.citaAdapter?.addData(cita)
                    }
                }
            }else{
                Toast.makeText(this.context,"No se puede elegir una fecha anterior a hoy ni hoy, ni para el año que viene", LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this.context,"Deben de estar todos los campos rellenados", LENGTH_LONG).show()
        }
    }

}