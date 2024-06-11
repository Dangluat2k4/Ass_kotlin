package com.example.ass_kotlin_luatdcph35698.Service

import com.example.ass_kotlin_luatdcph35698.DTO.AccountRespone
import com.example.ass_kotlin_luatdcph35698.DTO.CartRespone
import com.example.ass_kotlin_luatdcph35698.Model.Account
import com.example.ass_kotlin_luatdcph35698.Model.Cart
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CartServiceSV {
    @GET("http://10.0.2.2:3000/apiCart/cart/")
    suspend fun getListCart(): Response<List<CartRespone>>

    @POST("http://10.0.2.2:3000/apiCart/addToCart/")
    suspend fun  addCart(@Body cart: Cart) : Response<CartRespone>
}
