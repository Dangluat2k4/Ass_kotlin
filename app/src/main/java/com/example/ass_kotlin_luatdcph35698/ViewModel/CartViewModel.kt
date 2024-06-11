package com.example.ass_kotlin_luatdcph35698.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ass_kotlin_luatdcph35698.DTO.CartRespone
import com.example.ass_kotlin_luatdcph35698.Model.Account
import com.example.ass_kotlin_luatdcph35698.Model.Cart
import com.example.ass_kotlin_luatdcph35698.Model.ProductDT
import com.example.ass_kotlin_luatdcph35698.Retrofit.CartService
import com.example.ass_kotlin_luatdcph35698.toCart
import kotlinx.coroutines.launch
import retrofit2.Response

class CartViewModel : ViewModel() {
    private val _cart = MutableLiveData<List<Cart>>()
    val cart: LiveData<List<Cart>> = _cart

    init {
        getCart()
    }

    fun getCart() {
        viewModelScope.launch {
            try {
                val response = CartService().cartService.getListCart()
                if (response.isSuccessful) {
                    _cart.postValue(response.body()?.map { it.toCart() })
                } else {
                    _cart.postValue(emptyList())
                }
            } catch (e: Exception) {
                Log.e("TAG", "getMovies: " + e.message)
                _cart.postValue(emptyList())
            }
        }
    }


}