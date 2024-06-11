package com.example.ass_kotlin_luatdcph35698

import com.example.ass_kotlin_luatdcph35698.DTO.AccountRespone
import com.example.ass_kotlin_luatdcph35698.DTO.CartRespone
import com.example.ass_kotlin_luatdcph35698.DTO.ProductResponse
import com.example.ass_kotlin_luatdcph35698.Model.Account
import com.example.ass_kotlin_luatdcph35698.Model.Cart
import com.example.ass_kotlin_luatdcph35698.Model.ProductDT

fun ProductResponse.toProduct(): ProductDT {
    return ProductDT(
        _id = this._id,
        ProductName = this.ProductName,
        Description = this.Description,
        Price = this.Price.toString(),
        Image = this.Image,
        Status = this.Status
    )
}

fun AccountRespone.toAccount() : Account{
    return   Account(
        PassWord = this.PassWord,
        FullName = this.FullName,
        Avatar = this.Avatar
    )
}

fun CartRespone.toCart(): Cart{
    return  Cart(
        ProductID = this.ProductID,
        Name = this.Name,
        Price = this.Price.toString(),
        Description = this.Description,
        Quantity = this.Quantity,
        //IDAccount = this.IDAccount,
        Image = this.Image,
    )
}