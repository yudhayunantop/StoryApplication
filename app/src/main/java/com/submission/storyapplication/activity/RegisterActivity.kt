package com.submission.storyapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.submission.storyapplication.R
import com.submission.storyapplication.api.ApiRetrofit
import com.submission.storyapplication.helper.Resources
import com.submission.storyapplication.models.ResponseModel
import com.submission.storyapplication.preferences.Preferences
import com.submission.storyapplication.viewModel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var name: EditText
    private lateinit var password: EditText
    private lateinit var progressBar: ProgressBar
    private val RegisterViewModel: RegisterViewModel by viewModel()
    private var counter: Int = 0
    private var isLoading= MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_register)

        setupView()
        isLoading.value=false
        loadingObserver()
    }

    fun setupView(){
        email = findViewById(R.id.ed_register_email)
        name = findViewById(R.id.ed_register_name)
        password = findViewById(R.id.ed_register_password)
        progressBar= findViewById(R.id.progressBar)

        btn_register.setOnClickListener {
            if (name.text.toString().isNotEmpty() &&
                email.text.toString().isNotEmpty() &&
                password.text.toString().isNotEmpty()
            ) {
                if (counter==0){
                    registerAccount(name.text.toString(), email.text.toString(), password.text.toString())
                    counter++
                }else{
                    Toast.makeText(applicationContext, "Loading...", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(applicationContext, "Complete all requirement!!!", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun registerAccount(name:String,email:String,password:String){
        if (email.isNotEmpty() &&
            name.isNotEmpty() &&
            password.isNotEmpty()){
            Log.e("RegisterActivity", email)
            Log.e("RegisterActivity", name)
            Log.e("RegisterActivity", password)

            val coroutineScope = RegisterViewModel.viewModelScope
            coroutineScope.launch {
                RegisterViewModel.register(name, email, password).flowOn(
                    Dispatchers.IO
                ).collect { result ->
                    when (result) {
                        is Resources.Success -> {
                            startActivity(
                                Intent(this@RegisterActivity, LoginActivity::class.java)
                            )
                            finish()
                        }
                        is Resources.Error -> {
                            isLoading.value=false
                            Toast.makeText(applicationContext, result.message, Toast.LENGTH_SHORT)
                                .show()
                            Log.e("RegisterActivity", result.message.toString())
                        }
                        is Resources.Loading -> {
                            isLoading.value=true
                        }
                    }
                }
            }
        }
    }

    private fun loadingObserver(){
        isLoading.observe(this@RegisterActivity){ loadingState->
            if(loadingState){
                progressBar.visibility= View.VISIBLE
            }else{
                progressBar.visibility= View.GONE
            }
        }
    }
}