package com.example.ass_kotlin_luatdcph35698

import android.content.Context
import android.content.SharedPreferences
import com.example.ass_kotlin_luatdcph35698.Model.Account
import com.example.ass_kotlin_luatdcph35698.Model.AccountLG

object Preferences {
    private const val PREFS_NAME = "account_prefs"
    private const val KEY_USERNAME = "username"
    private const val KEY_PASSWORD = "password"

    fun saveAccount(context: Context, username: String, password: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_USERNAME, username)
        editor.putString(KEY_PASSWORD, password)
        editor.apply()
    }

    fun getAccount(context: Context): AccountLG? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val username = sharedPreferences.getString(KEY_USERNAME, null)
        val password = sharedPreferences.getString(KEY_PASSWORD, null)
        return if (username != null && password != null) {
            AccountLG(username, password)
        } else {
            null
        }
    }

    fun clearAccount(context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
