package com.afsal.dev.filimapp.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.afsal.dev.filimapp.R
import com.afsal.dev.filimapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            setSupportActionBar(binding.toolbar2)
       val navHostFragment=  supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment

        val navController=navHostFragment.navController

        setupActionBarWithNavController(navController)
        supportActionBar?.title=customTittleText()
       // binding.toolbar2.title =

    }

    private fun customTittleText():SpannableString{
        val spannebleString=SpannableString("NobarFILM")

        val fColorFirst= ForegroundColorSpan(Color.WHITE)
        val fColorSecond= ForegroundColorSpan(Color.BLACK)
         val backgroundSecond=BackgroundColorSpan(Color.RED)

        spannebleString.setSpan(fColorFirst,0,5,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannebleString.setSpan(fColorSecond,5,9,Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        spannebleString.setSpan(backgroundSecond,5,9,Spannable.SPAN_INCLUSIVE_EXCLUSIVE)


        return    spannebleString
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView2)

        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}