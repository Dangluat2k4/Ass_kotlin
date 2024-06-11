package com.example.ass_kotlin_luatdcph35698.Service

import com.example.ass_kotlin_luatdcph35698.DTO.AccountRP
import com.example.ass_kotlin_luatdcph35698.DTO.AccountRespone
import com.example.ass_kotlin_luatdcph35698.Model.Account
import com.example.ass_kotlin_luatdcph35698.Model.AccountCT
import com.example.ass_kotlin_luatdcph35698.Model.AccountLG
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AccountService {
    @GET("http://10.0.2.2:3000/apiAc/account/")
    suspend fun getListAccount(): Response<List<AccountRespone>>

    @POST("http://10.0.2.2:3000/apiAC/addAC/")
    suspend fun  addAccount(@Body account: Account) : Response<AccountRespone>

    @POST("http://10.0.2.2:3000/apiAc/login/")
    suspend fun login(@Body account: AccountLG): Response<AccountRP>

    @PUT("http://10.0.2.2:3000/apiAc/account/{id}")
    suspend fun updateAccount(@Path("id") id: String, @Body account: AccountCT): Response<AccountRespone>
}