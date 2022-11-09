package com.example.serviceandroid

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.example.serviceandroid.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    companion object {
        const val CHANNEL_ID = "channel_id"
        const val NOTIFICATION_ID = 1
    }


    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    val fm = supportFragmentManager
    private var numero = 0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /*binding.btnIservice.setOnClickListener {
            Intent(this, MiService::class.java).also {
                startService(it)

            }
        }

        binding.btnCservice.setOnClickListener {
            Intent(this, MiService::class.java).also {
                stopService(it)
            }
        }*/

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()
        appBarConfiguration = AppBarConfiguration(
            navController.graph,
            binding.drawerLayout,

        )



        //Toolbar
        setSupportActionBar(binding.topAppBar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // DrawerLayout
        //binding.navigationView.setupWithNavController(navController)


        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> {
                   // navHostFragment.navController.popBackStack(R.id.action_mainFragment_to_inicioFragment, true)
                navHostFragment.navController.navigate(R.id.inicioFragment)
                }
                R.id.menu_recepcion -> {

                    for (x in  1..navHostFragment.childFragmentManager.backStackEntryCount) {
                        navHostFragment.navController.popBackStack()
                    }
                    Log.d("AQUI", navHostFragment.childFragmentManager.backStackEntryCount.toString())
                    navHostFragment.navController.navigate(R.id.thredFragment)
                }
            }
            binding.drawerLayout.close()
            true
        }
    }

    private fun replace(fragment: Fragment) {
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.fragmentContainerView, fragment)
        manager.commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragmentContainerView).navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return item.onNavDestinationSelected(findNavController(R.id.fragmentContainerView))
                || super.onOptionsItemSelected(item)
    }








    override fun onDestroy() {
        super.onDestroy()
        Log.d("MiServicio", "DESTROY ACTIVITY")
    }
}