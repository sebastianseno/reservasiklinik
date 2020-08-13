package com.dodolife.rapidnews.ui

import android.os.Bundle
import androidx.navigation.findNavController
import com.dodolife.rapidnews.R
import com.dodolife.rapidnews.modules.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController(R.id.navHost)
        navController.setGraph(R.navigation.nav_main)

    }
}