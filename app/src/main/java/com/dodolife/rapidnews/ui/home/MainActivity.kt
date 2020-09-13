package com.dodolife.rapidnews.ui.home

import android.os.Bundle
import androidx.navigation.findNavController
import com.dodolife.rapidnews.R
import com.dodolife.rapidnews.modules.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController(R.id.navHost)
        navController.setGraph(R.navigation.nav_main)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    searchBar.hint = "Cari Klinik / Rumah Sakit"
                }
                R.id.doctorListFragment -> {
                    searchBar.hint = "Cari Dokter"

                }
            }
        }
    }
}