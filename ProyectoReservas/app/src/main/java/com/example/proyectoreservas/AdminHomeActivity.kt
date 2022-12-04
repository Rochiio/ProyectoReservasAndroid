package com.example.proyectoreservas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.proyectoreservas.databinding.ActivityAdminHomeBinding
import com.example.proyectoreservas.databinding.ActivityUserHomeBinding
import com.example.proyectoreservas.fragments.*
import com.example.proyectoreservas.models.Data

class AdminHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminHomeBinding
    private lateinit var mFragmentManager: FragmentManager
    private lateinit var mActivityFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpFragment()
    }

    private fun setUpFragment() {
        mFragmentManager = supportFragmentManager

        val home = AdminHomeFragment()
        val add = AdminAddFragment()

        mActivityFragment = home


        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, add, AdminAddFragment::class.java.name)
            .hide(add)
            .commit()

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, home, AdminHomeFragment::class.java.name)
            .commit()

        binding.buttonNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.action_home_admin ->{
                    mFragmentManager.beginTransaction().hide(mActivityFragment).show(home).commit()
                    mActivityFragment= home
                    true
                }

                R.id.action_add_admin ->{
                    mFragmentManager.beginTransaction().hide(mActivityFragment).show(add).commit()
                    mActivityFragment= add
                    true
                }

                else -> false
            }
        }
    }
}