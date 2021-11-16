package com.example.tes_pitjarus.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tes_pitjarus.R
import com.example.tes_pitjarus.databinding.ActivityLoginBinding
import com.example.tes_pitjarus.utils.exhaustive
import com.example.tes_pitjarus.utils.gone
import com.example.tes_pitjarus.utils.pref.PrefManager
import com.example.tes_pitjarus.utils.pref.UserPreferenceKey
import com.example.tes_pitjarus.utils.viewmodel.ResultWrapper
import com.example.tes_pitjarus.utils.visible
import com.example.tes_pitjarus.viewmodels.LoginViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModel()
    private val pref: PrefManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (pref.getBoolean(UserPreferenceKey.IS_LOGGED_IN)){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            initUI()
        }
    }

    private fun initUI(){
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initAction()
        initObservable()
    }

    private fun initAction(){
        binding.btnLogin.setOnClickListener {
            when{
                binding.edtUsername.text.isNullOrEmpty() && binding.edtPassword.text.isNullOrEmpty() -> Toast.makeText(this, "Username dan Password harus diisi", Toast.LENGTH_SHORT).show()
                binding.edtUsername.text.isNullOrEmpty() -> Toast.makeText(this, "Username harus diisi", Toast.LENGTH_SHORT).show()
                binding.edtPassword.text.isNullOrEmpty() -> Toast.makeText(this, "Password harus diisi", Toast.LENGTH_SHORT).show()
                else -> {
                    loginViewModel.login(binding.edtUsername.text.toString(), binding.edtPassword.text.toString())
                }
            }
        }
        binding.btnCheckUpdate.setOnClickListener {  }
    }

    private fun initObservable(){
        loginViewModel.loginData.observe(this, {
            when(it) {
                is ResultWrapper.Failure -> {
                    binding.progressLogin.gone()
                    binding.btnLogin.text = "Login"
                    binding.btnLogin.isClickable = true
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is ResultWrapper.Loading -> {
                    binding.progressLogin.visible()
                    binding.btnLogin.text = ""
                    binding.btnLogin.isClickable = false
                }
                is ResultWrapper.Success -> {
                    binding.progressLogin.gone()
                    binding.btnLogin.text = "Success"
                    binding.btnLogin.isClickable = true
                    pref.saveBoolean(UserPreferenceKey.IS_LOGGED_IN, true)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        })
    }
}