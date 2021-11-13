package com.example.tes_pitjarus.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.tes_pitjarus.R
import com.example.tes_pitjarus.databinding.ActivityLoginBinding
import com.example.tes_pitjarus.databinding.ActivityMainBinding
import com.example.tes_pitjarus.utils.exhaustive
import com.example.tes_pitjarus.utils.viewmodel.ResultWrapper
import com.example.tes_pitjarus.viewmodels.LoginViewModel
import com.example.tes_pitjarus.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initActions()
    }

    private fun initActions(){
        binding.layoutMenu.llMenuKunjungan.setOnClickListener {  }
        binding.layoutMenu.llMenuTarget.setOnClickListener {  }
        binding.layoutMenu.llMenuDashboard.setOnClickListener {  }
        binding.layoutMenu.llMenuHistory.setOnClickListener {  }
        binding.layoutMenu.llMenuLogout.setOnClickListener {  }

    }
}