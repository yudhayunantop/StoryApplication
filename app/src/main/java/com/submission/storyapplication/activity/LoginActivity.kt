package com.submission.storyapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.submission.storyapplication.R
import com.submission.storyapplication.api.ApiRetrofit
import com.submission.storyapplication.models.LoginModel
import com.submission.storyapplication.preferences.Preferences
import com.submission.storyapplication.preferences.Preferences.preferences
import com.submission.storyapplication.preferences.Preferences.saveName
import com.submission.storyapplication.preferences.Preferences.saveToken
import com.submission.storyapplication.preferences.Preferences.saveUserId
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint}
    private lateinit var email: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Preferences.init(this)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_login)


        if(preferences.contains("KEY_userId")&&
            preferences.contains("KEY_name")&&
            preferences.contains("key_token")){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        setupView()
    }

    fun setupView(){
        email = findViewById(R.id.ed_login_email)
        password = findViewById(R.id.ed_login_password)

        btn_login.setOnClickListener {
            checkLogin(email.text.toString(), password.text.toString())
        }
        txt_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun checkLogin(username:String, password:String){
        if (username.isNotEmpty() &&
            password.isNotEmpty()){
            Log.e("LoginActivity", username)
            Log.e("LoginActivity", password)

            api.login(username, password)
                .enqueue(object : Callback<LoginModel>{
                    override fun onResponse(
                        call: Call<LoginModel>,
                        response: Response<LoginModel>
                    ) {
                        if (response.isSuccessful){
                            val submit = response.body()
                            if (submit!!.error==false){

                                saveUserId(submit.loginResult!!.userId!!)
                                saveName(submit.loginResult.name!!)
                                saveToken(submit.loginResult.token!!)
                                startActivity(
                                    Intent(this@LoginActivity, MainActivity::class.java)
                                )
                                Toast.makeText(applicationContext, submit.message, Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            else{
                                Toast.makeText(applicationContext, submit.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                        Toast.makeText(applicationContext, "No Connection!!!", Toast.LENGTH_SHORT).show()
                    }

                })
        }
        else{
            Toast.makeText(applicationContext, "Username / password empty!!!", Toast.LENGTH_SHORT).show()
        }
    }
}