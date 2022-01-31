package com.example.inote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity()
{
    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?)
    {
        actionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // manages app navigation within NavHost
        navController = findNavController(R.id.fragmentContainerView)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}