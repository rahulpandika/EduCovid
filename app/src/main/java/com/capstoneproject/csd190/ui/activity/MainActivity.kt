package com.capstoneproject.csd190.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.capstoneproject.csd190.R
import com.capstoneproject.csd190.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigation

        val navController = findNavController(R.id.navigation_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_statistic,
                R.id.navigation_prevention,
                R.id.navigation_account
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)

        supportActionBar?.elevation = 0f
    }

    // Show Dialog
    private fun showDialog() {
        val view = View.inflate(this@MainActivity, R.layout.fragment_dialog, null)

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val btn = view.findViewById<Button>(R.id.btn_back)
        btn.setOnClickListener {
            dialog.dismiss()
        }

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val displayWidth = displayMetrics.widthPixels

        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window!!.attributes)

        val dialogWindowWidth = (displayWidth * 0.96f).toInt()
        layoutParams.width = dialogWindowWidth

        dialog.window!!.attributes = layoutParams
    }

    // Create Options Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    // Selected Options Item
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.item_maps -> {
            startActivity(Intent(this, MapActivity::class.java))

            true
        }

        R.id.item_info_apps -> {
            showDialog()

            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}