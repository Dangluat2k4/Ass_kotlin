package com.example.ass_kotlin_luatdcph35698.DTO

import com.google.gson.annotations.SerializedName

data class AccountRespone(
    @SerializedName("PassWord") val PassWord :String,
    @SerializedName("FullName") val FullName :String,
    @SerializedName("Avatar") val Avatar :String,
)
