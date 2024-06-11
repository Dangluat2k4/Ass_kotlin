package com.example.ass_kotlin_luatdcph35698.Service

import com.example.ass_kotlin_luatdcph35698.DTO.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductService {
    @GET("http://10.0.2.2:3000/api/products/")
    suspend fun getListFilms(): Response<List<ProductResponse>>

    @POST("http://10.0.2.2:3000/api/updateStatus/{id}")
    suspend fun updateProductStatus(@Path("id") id:String, @Body status : Map<String, Int>) : Response<ProductResponse>

    @POST("http://10.0.2.2:3000/api/updatStatusFV/{id}")
    suspend fun updateUnProductStatus(@Path("id") id:String, @Body status : Map<String, Int>) : Response<ProductResponse>
}
