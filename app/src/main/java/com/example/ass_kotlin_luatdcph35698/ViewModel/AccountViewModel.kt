package com.example.ass_kotlin_luatdcph35698.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ass_kotlin_luatdcph35698.DTO.AccountRespone
import com.example.ass_kotlin_luatdcph35698.Model.AccountCT

import com.example.ass_kotlin_luatdcph35698.Model.AccountLG
import com.example.ass_kotlin_luatdcph35698.Preferences
import com.example.ass_kotlin_luatdcph35698.Retrofit.AccountRetrofit
import kotlinx.coroutines.launch
import retrofit2.Response

class  AccountViewModel : ViewModel(){
    private val _account = MutableLiveData<AccountLG?>()
    val account: LiveData<AccountLG?> get() = _account

    fun loadAccount(context: Context) {
        val savedAccount = Preferences.getAccount(context)
        _account.value = savedAccount
    }
    fun updateAccount(id: String, account: AccountCT, onResult: (Response<AccountRespone>) -> Unit) {
        viewModelScope.launch {
            val response = AccountRetrofit().accountService.updateAccount(id, account)
            onResult(response)
        }
    }
}