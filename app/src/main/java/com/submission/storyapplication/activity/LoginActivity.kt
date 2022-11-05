package com.submission.storyapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.submission.storyapplication.R
import com.submission.storyapplication.api.ApiRetrofit
import com.submission.storyapplication.helper.Resources
import com.submission.storyapplication.models.LoginModel
import com.submission.storyapplication.preferences.Preferences
import com.submission.storyapplication.preferences.Preferences.preferences
import com.submission.storyapplication.preferences.Preferences.saveName
import com.submission.storyapplication.preferences.Preferences.saveToken
import com.submission.storyapplication.preferences.Preferences.saveUserId
import com.submission.storyapplication.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private val LoginViewModel: LoginViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Preferences.init(this)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_login)


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
        email = findViewById(R.id.ed_login_email)
        password = findViewById(R.id.ed_login_password)

        btn_login.setOnClickListener {
            checkLogin(email.text.toString(), password.text.toString())
        }
        txt_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun checkLogin(username: String, password: String) {
        if (username.isNotEmpty() &&
            password.isNotEmpty()
        ) {
            Log.e("LoginActivity", username)
            Log.e("LoginActivity", password)

            val coroutineScope = LoginViewModel.viewModelScope
            coroutineScope.launch {
                LoginViewModel.login(username, password).flowOn(
                    Dispatchers.IO
                ).collect { result ->
                    when (result) {
                        is Resources.Success -> {
                            saveUserId(result.data!!.userId!!)
                            saveName(result.data.name!!)
                            saveToken(result.data.token!!)
                            startActivity(
                                Intent(this@LoginActivity, MainActivity::class.java)
                            )
                            finish()
                        }
                        is Resources.Error -> {
                            Toast.makeText(applicationContext, result.message, Toast.LENGTH_SHORT)
                                .show()
                            Log.e("LoginActivity", result.message.toString())
                        }
                        is Resources.Loading -> {

                        }
                    }
                }
            }
        } else {
            Toast.makeText(applicationContext, "Username / password empty!!!", Toast.LENGTH_SHORT)
                .show()
        }
    }
}