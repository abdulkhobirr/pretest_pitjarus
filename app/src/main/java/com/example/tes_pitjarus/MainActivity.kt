package com.example.tes_pitjarus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.tes_pitjarus.viewmodels.LoginViewModel
import com.example.tes_pitjarus.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val loginViewModel : LoginViewModel by viewModel()
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginViewModel.login("pitjarus", "admin")

        Handler(Looper.getMainLooper()).postDelayed({
            mainViewModel.getStores()
        }, 2000)
    }
}