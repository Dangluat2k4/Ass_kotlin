package com.example.ass_kotlin_luatdcph35698.DTO

import com.google.gson.annotations.SerializedName

data  class CartRespone (
    @SerializedName("ProductID") val ProductID :String,
    @SerializedName("Name") val Name :String,
    @SerializedName("Price") val Price :Int,
    @SerializedName("Description") val Description :String,
    @SerializedName("Quantity") val Quantity :Int,
    @SerializedName("IDAccount") val IDAccount :String,
    @SerializedName("Image") val Image :String,
)