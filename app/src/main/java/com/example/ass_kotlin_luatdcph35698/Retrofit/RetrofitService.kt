package com.example.ass_kotlin_luatdcph35698.Retrofit

import com.example.ass_kotlin_luatdcph35698.Service.ProductService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class RetrofitService() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/api/products/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val productService: ProductService by lazy {
        retrofit.create(ProductService::class.java)
    }
}