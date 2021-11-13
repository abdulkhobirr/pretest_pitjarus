package com.example.tes_pitjarus.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.tes_pitjarus.R
import com.example.tes_pitjarus.utils.exhaustive
import com.example.tes_pitjarus.utils.viewmodel.ResultWrapper
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

    private fun initObservables(){
        loginViewModel.loginData.observe(this, {
            when(it){
                is ResultWrapper.Default -> TODO()
                is ResultWrapper.Empty -> TODO()
                is ResultWrapper.Failure -> TODO()
                is ResultWrapper.Loading -> TODO()
                is ResultWrapper.Success -> TODO()
            }.exhaustive
        })
    }
}