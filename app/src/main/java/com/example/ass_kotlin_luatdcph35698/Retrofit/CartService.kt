package com.example.ass_kotlin_luatdcph35698.Retrofit

import com.example.ass_kotlin_luatdcph35698.Service.AccountService
import com.example.ass_kotlin_luatdcph35698.Service.CartServiceSV
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open  class CartService(){
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cartService: CartServiceSV by lazy {
        retrofit.create(CartServiceSV::class.java)
    }
}