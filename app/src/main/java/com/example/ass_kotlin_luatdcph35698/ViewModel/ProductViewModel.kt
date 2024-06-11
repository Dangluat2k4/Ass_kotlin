package com.example.ass_kotlin_luatdcph35698.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ass_kotlin_luatdcph35698.DTO.CartRespone
import com.example.ass_kotlin_luatdcph35698.DTO.ProductResponse
import com.example.ass_kotlin_luatdcph35698.Model.Account
import com.example.ass_kotlin_luatdcph35698.Model.AccountCT
import com.example.ass_kotlin_luatdcph35698.Model.Cart
import com.example.ass_kotlin_luatdcph35698.Model.ProductDT
import com.example.ass_kotlin_luatdcph35698.Retrofit.CartService
import com.example.ass_kotlin_luatdcph35698.Retrofit.RetrofitService
import com.example.ass_kotlin_luatdcph35698.toProduct
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductViewModel : ViewModel() {
    private val _product = MutableLiveData<List<ProductDT>>()
    val product: LiveData<List<ProductDT>> = _product

//    private val _accountCT = MutableLiveData<List<AccountCT>>()
//    val accountCT: LiveData<List<AccountCT>> = _accountCT

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            try {
                val response = RetrofitService().productService.getListFilms()
                if (response.isSuccessful) {
                    _product.postValue(response.body()?.map { it.toProduct() })
                } else {
                    _product.postValue(emptyList())
                }
            } catch (e: Exception) {
                Log.e("TAG", "GetProduct: " + e.message)
                _product.postValue(emptyList())
            }
        }
    }

    fun updateProductStatus(
        id: String,
        status: Int,
        onResponse: (Response<ProductResponse>) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = RetrofitService().productService.updateProductStatus(
                    id,
                    mapOf("Status" to status)
                )
                onResponse(response)
            } catch (e: Exception) {
                Log.e("TAG", "GetProduct: " + e.message)
                _product.postValue(emptyList())
            }
        }
    }

    fun updateUnProductStatus(
        id: String,
        status: Int,
        onResponse: (Response<ProductResponse>) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = RetrofitService().productService.updateUnProductStatus(
                    id,
                    mapOf("Status" to status)
                )
                onResponse(response)
            } catch (e: Exception) {
                Log.e("TAG", "Get Product" + e.message)
                _product.postValue(emptyList())
            }
        }
    }

    fun loadProductList() {
        viewModelScope.launch {
            try {
                val response = RetrofitService().productService.getListFilms()
                if (response.isSuccessful) {
                    _product.value = response.body()?.map { it.toProduct() }
                } else {
                    // Xử lý khi không thành công
                    Log.e("ProductViewModel", "Failed to load product list: ${response.message()}")
                }
            } catch (e: Exception) {
                // Xử lý lỗi nếu có
                Log.e("ProductViewModel", "Exception while loading product list", e)
            }
        }
    }

    fun addToCart(product: ProductDT, onResponse: (Response<CartRespone>) -> Unit) {
        viewModelScope.launch {
            try {
                val cart = Cart(
                    ProductID = product._id,
                    Name = product.ProductName,
                    Price = product.Price,
                    Description = product.Description,
                    Quantity = 1,
//                    IDAccount = account._id,
                    Image = product.Image
                )
                val response = CartService().cartService.addCart(cart)
                onResponse(response)
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Failed to add product to cart", e)
            }
        }
    }
}
