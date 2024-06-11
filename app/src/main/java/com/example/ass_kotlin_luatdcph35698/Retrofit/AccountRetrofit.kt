package com.example.ass_kotlin_luatdcph35698.Retrofit

import com.example.ass_kotlin_luatdcph35698.Service.AccountService
import com.example.ass_kotlin_luatdcph35698.Service.ProductService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open  class AccountRetrofit(){
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val accountService: AccountService by lazy {
        retrofit.create(AccountService::class.java)
    }
}