package com.example.proyectoreservas

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.proyectoreservas.databinding.ActivityUserHomeBinding
import com.example.proyectoreservas.fragments.AddFragment
import com.example.proyectoreservas.fragments.HomeFragment
import com.example.proyectoreservas.fragments.ProfileFragment
import com.example.proyectoreservas.models.Data
import com.example.proyectoreservas.models.Usuario

class UserHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserHomeBinding
    private lateinit var mFragmentManager: FragmentManager
    private lateinit var mActivityFragment: Fragment
    private lateinit var user: Usuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Usuario
        user = Data.usuarioActual!!

        setUpFragment()
    }

    private fun setUpFragment() {
        binding.buttonNav.itemTextColor=ColorStateList.valueOf(getResources().getColor(R.color.fuxia))
        mFragmentManager = supportFragmentManager

        val home = HomeFragment()
        val add = AddFragment()
        val profile = ProfileFragment()

        mActivityFragment = home

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, profile, ProfileFragment::class.java.name)
            .hide(profile)
            .commit()

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, add, AddFragment::class.java.name)
            .hide(add)
            .commit()

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, home, HomeFragment::class.java.name)
            .commit()

        binding.buttonNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.action_home ->{
                    binding.textFragment.text="Mis Citas"
                    mFragmentManager.beginTransaction().hide(mActivityFragment).show(home).commit()
                    mActivityFragment= home
                    true
                }

                R.id.action_add ->{
                    binding.textFragment.text="Añadir Cita"
                    mFragmentManager.beginTransaction().hide(mActivityFragment).show(add).commit()
                    mActivityFragment= add
                    true
                }

                R.id.action_about ->{
                    binding.textFragment.text="Acerca de"
                    mFragmentManager.beginTransaction().hide(mActivityFragment).show(profile).commit()
                    mActivityFragment= profile
                    true
                }
                else -> false
            }
        }
    }




}