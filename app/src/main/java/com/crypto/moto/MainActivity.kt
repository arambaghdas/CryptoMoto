package com.crypto.moto

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.crypto.moto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)
                as NavHostFragment
        navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)
        setOnClickListeners()
        activateServices()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setOnClickListeners() {
        binding.settingsIv.setOnClickListener {
            showToastMessageUnderConstruction()
        }
        binding.bookmarkIv.setOnClickListener {
            showToastMessageUnderConstruction()
        }
        binding.addIv.setOnClickListener {
            showToastMessageUnderConstruction()
        }
        binding.backIv.setOnClickListener {
            finish()
        }

        binding.postsBt.setOnClickListener {
            navController.navigate(R.id.navigation_posts)
            updateViewDrawable(binding.postsBt, R.drawable.button_gradient_active)
            updateViewDrawable(binding.servicesBt, R.drawable.button_gradient_inactive)
            updateViewDrawable(binding.shopsBt, R.drawable.button_gradient_inactive)
            updateViewDrawable(binding.usersBt, R.drawable.button_gradient_inactive)
        }

        binding.servicesBt.setOnClickListener {
            navController.navigate(R.id.navigation_services)
            activateServices()
        }
        binding.shopsBt.setOnClickListener {
            navController.navigate(R.id.navigation_shops)
            updateViewDrawable(binding.shopsBt, R.drawable.button_gradient_active)
            updateViewDrawable(binding.postsBt, R.drawable.button_gradient_inactive)
            updateViewDrawable(binding.servicesBt, R.drawable.button_gradient_inactive)
            updateViewDrawable(binding.usersBt, R.drawable.button_gradient_inactive)
        }

        binding.usersBt.setOnClickListener {
            navController.navigate(R.id.navigation_users)
            updateViewDrawable(binding.usersBt, R.drawable.button_gradient_active)
            updateViewDrawable(binding.postsBt, R.drawable.button_gradient_inactive)
            updateViewDrawable(binding.servicesBt, R.drawable.button_gradient_inactive)
            updateViewDrawable(binding.shopsBt, R.drawable.button_gradient_inactive)
        }

        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.navigation_services)
                    activateServices()
                }
                R.id.navigation_chat -> {
                    navController.navigate(R.id.navigation_chat)
                }
                R.id.navigation_profile -> {
                    navController.navigate(R.id.navigation_profile)
                }
                R.id.navigation_map -> {
                    navController.navigate(R.id.navigation_map)
                }
                R.id.navigation_menu -> {
                    navController.navigate(R.id.navigation_menu)
                }
            }
            true
        }
    }

    private fun updateViewDrawable(view: View, iconId: Int) {
        val drawable = ContextCompat.getDrawable(this, iconId)
        view.background = drawable

        val radius = resources.getDimension(R.dimen.corner_round)
        val shapeDrawable = view.background as GradientDrawable
        shapeDrawable.cornerRadius = radius
    }

    private fun activateServices() {
        updateViewDrawable(binding.servicesBt, R.drawable.button_gradient_active)
        updateViewDrawable(binding.postsBt, R.drawable.button_gradient_inactive)
        updateViewDrawable(binding.shopsBt, R.drawable.button_gradient_inactive)
        updateViewDrawable(binding.usersBt, R.drawable.button_gradient_inactive)
    }

    private fun showToastMessageUnderConstruction() {
        Toast.makeText(this, getString(R.string.under_construction), Toast.LENGTH_SHORT).show()
    }
}