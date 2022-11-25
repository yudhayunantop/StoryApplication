package com.submission.storyapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.viewModelScope
import com.submission.storyapplication.R
import com.submission.storyapplication.databinding.ActivityLoginBinding
import com.submission.storyapplication.core.helper.Resources
import com.submission.storyapplication.core.preferences.Preferences
import com.submission.storyapplication.core.preferences.Preferences.preferences
import com.submission.storyapplication.core.preferences.Preferences.saveName
import com.submission.storyapplication.core.preferences.Preferences.saveToken
import com.submission.storyapplication.core.preferences.Preferences.saveUserId
import com.submission.storyapplication.viewModel.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val LoginViewModel: LoginViewModel by viewModel()
    private var counter: Int = 0
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.visibility= View.GONE

        Preferences.init(this)

        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        if (preferences.contains("KEY_userId") &&
            preferences.contains("KEY_name") &&
            preferences.contains("key_token")
        ) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        setupView()
    }

    fun setupView() {

        binding.btnLogin.setOnClickListener {
            if (binding.edLoginEmail.text.toString().isNotEmpty() &&
                binding.edLoginPassword.text.toString().isNotEmpty()
            ) {
                if (counter==0){
                    checkLogin(binding.edLoginEmail.text.toString(), binding.edLoginPassword.text.toString())
                    counter++
                }else{
                    Toast.makeText(applicationContext, "Loading...", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(applicationContext, "Username / password empty!!!", Toast.LENGTH_SHORT)
                    .show()
            }

        }
        binding.txtRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun checkLogin(username: String, password: String) {

            Log.e("LoginActivity", username)
            Log.e("LoginActivity", password)

            val coroutineScope = LoginViewModel.viewModelScope
            coroutineScope.launch {
                LoginViewModel.login(username, password).flowOn(
                    Dispatchers.IO
                ).collect { result ->
                    when (result) {
                        is Resources.Success -> {
                            binding.progressBar.visibility= View.GONE
                            saveUserId(result.data!!.userId!!)
                            saveName(result.data!!.name!!)
                            saveToken(result.data!!.token!!)
                            startActivity(
                                Intent(this@LoginActivity, MainActivity::class.java)
                            )
                            finish()
                        }
                        is Resources.Error -> {
                            binding.progressBar.visibility= View.GONE
                            Toast.makeText(applicationContext, result.message, Toast.LENGTH_SHORT)
                                .show()
                            Log.e("LoginActivity", result.message.toString())
                        }
                        is Resources.Loading -> {
                            binding.progressBar.visibility= View.VISIBLE
                        }
                    }
                }
            }

    }
}