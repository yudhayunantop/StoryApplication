package com.submission.storyapplication.core.utils

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.securepreferences.SecurePreferences

object Preferences {
    private const val PREFS_NAME = "DataLogin"
//    private const val MODE = Context.MODE_PRIVATE
    private const val KEY_userId = "KEY_userId"
    private const val KEY_name = "KEY_name"
    private const val KEY_token = "key_token"
    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
//        preferences = context.getSharedPreferences(PREFS_NAME, MODE)
        preferences = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val spec = KeyGenParameterSpec.Builder(
                MasterKey.DEFAULT_MASTER_KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build()
            val masterKey = MasterKey.Builder(context)
                .setKeyGenParameterSpec(spec)
                .build()
            EncryptedSharedPreferences
                .create(
                    context,
                    PREFS_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
        } else {
            SecurePreferences(context)
        }
    }

    fun clearData(){
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.clear()
        editor.apply()
    }

    fun getToken(): String? = preferences.getString(KEY_token, null)

    fun saveUserId(userId: String){
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(KEY_userId, userId)
        editor.apply()
    }

    fun saveName(userId: String){
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(KEY_name, userId)
        editor.apply()
    }

    fun saveToken(userId: String){
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(KEY_token, userId)
        editor.apply()
    }
}