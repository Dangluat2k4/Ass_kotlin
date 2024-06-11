package com.example.ass_kotlin_luatdcph35698.DTO

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("_id") val _id:String,
    @SerializedName("ProductName") val ProductName: String,
    @SerializedName("Price") val Price: Int,
    @SerializedName("Description") val Description: String,
    @SerializedName("Image") val Image: String,
    @SerializedName("Status") val Status: Int,
)