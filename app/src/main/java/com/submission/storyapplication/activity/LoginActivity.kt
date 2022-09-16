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
import com.submission.storyapplication.models.LoginModel
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
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_login)

        setupView()
    }

    fun setupView(){
        email = findViewById(R.id.ed_login_email)
        password = findViewById(R.id.ed_login_password)

        btn_login.setOnClickListener {
            checkLogin(email.text.toString(), password.text.toString())
/*            if(btn_login.text.toString().equals("Show")){
                ed_login_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else{
                ed_login_password.transformationMethod = PasswordTransformationMethod.getInstance()
            }*/
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
                                Toast.makeText(applicationContext, submit.message, Toast.LENGTH_SHORT).show()
//                                Toast.makeText(
//                                    applicationContext,
//                                    submit.message,
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                saveNamaPerusahaan(submit.data[0].nama_perusahaan!!)
//                                saveNamaPT(submit.data[0].nama_pt!!)
//                                saveUsernameCust(submit.data[0].username!!)
//                                saveKeterangan(submit.data[0].keterangan!!)
//                                saveKereta(submit.data[0].kereta!!)
//                                saveMinContractVol(submit.data[0].min_contract_vol!!)
//                                startActivity(
//                                    Intent(this@LoginActivity, HomeActivity::class.java)
////                                    .putExtra("datauser", submit!!.data[0]))
//                                )
//                                finish()
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