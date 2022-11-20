package com.submission.storyapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.submission.storyapplication.R
import com.submission.storyapplication.databinding.ActivityRegisterBinding
import com.submission.storyapplication.core.helper.Resources
import com.submission.storyapplication.viewModel.RegisterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private val RegisterViewModel: RegisterViewModel by viewModel()
    private var counter: Int = 0
    private var isLoading= MutableLiveData<Boolean>()
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_register)

        setupView()
        isLoading.value=false
        loadingObserver()
    }

    fun setupView(){

        binding.btnRegister.setOnClickListener {
            if (binding.edRegisterName.text.toString().isNotEmpty() &&
                binding.edRegisterEmail.text.toString().isNotEmpty() &&
                binding.edRegisterPassword.text.toString().isNotEmpty()
            ) {
                if (counter==0){
                    registerAccount(binding.edRegisterName.text.toString(), binding.edRegisterEmail.text.toString(), binding.edRegisterPassword.text.toString())
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
                binding.progressBar.visibility= View.VISIBLE
            }else{
                binding.progressBar.visibility= View.GONE
            }
        }
    }
}