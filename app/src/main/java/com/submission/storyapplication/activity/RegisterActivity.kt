package com.submission.storyapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.EditText
import android.widget.Toast
import com.submission.storyapplication.R
import com.submission.storyapplication.api.ApiRetrofit
import com.submission.storyapplication.models.ResponseModel
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint}
    private lateinit var email: EditText
    private lateinit var name: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_register)

        setupView()
    }

    fun setupView(){
        email = findViewById(R.id.ed_register_email)
        name = findViewById(R.id.ed_register_name)
        password = findViewById(R.id.ed_register_password)

        btn_register.setOnClickListener {
            registerAccount(email.text.toString(), name.text.toString(), password.text.toString())
/*            if(btn_login.text.toString().equals("Show")){
                ed_login_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else{
                ed_login_password.transformationMethod = PasswordTransformationMethod.getInstance()
            }*/
        }
    }

    private fun registerAccount(email:String, name:String,password:String){
        if (email.isNotEmpty() &&
            name.isNotEmpty() &&
            password.isNotEmpty()){
            Log.e("RegisterActivity", email)
            Log.e("RegisterActivity", name)
            Log.e("RegisterActivity", password)

            api.register(name, email, password)
                .enqueue(object : Callback<ResponseModel> {
                    override fun onResponse(
                        call: Call<ResponseModel>,
                        response: Response<ResponseModel>
                    ) {
                        val submit = response.body()
                        if (response.isSuccessful){
                            if (submit!!.error==false){
                                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                                Toast.makeText(applicationContext, submit.message, Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            else{
                                Toast.makeText(applicationContext, submit.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                        Toast.makeText(applicationContext, "No Connection!!!", Toast.LENGTH_SHORT).show()
                    }

                })
        }
        else{
            Toast.makeText(applicationContext, "Complete all requirement!!!", Toast.LENGTH_SHORT).show()
        }
    }
}