package com.submission.storyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.submission.storyapplication.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupView()
    }

    fun setupView(){
        btn_login.setOnClickListener {
            if(btn_login.text.toString().equals("Show")){
                ed_login_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else{
                ed_login_password.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }
}