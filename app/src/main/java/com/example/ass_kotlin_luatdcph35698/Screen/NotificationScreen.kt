package com.example.ass_kotlin_luatdcph35698.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.ass_kotlin_luatdcph35698.Model.Cart
import com.example.ass_kotlin_luatdcph35698.Model.Product
import com.example.ass_kotlin_luatdcph35698.R
import com.example.ass_kotlin_luatdcph35698.ViewModel.CartViewModel

@Composable
fun NotificationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NotificationGrid()
    }
}

@Composable
fun NotificationGrid(CartViewModel: CartViewModel = viewModel()) {
    val cart = CartViewModel.cart.observeAsState(initial = emptyList())
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {

        items(cart.value.size) { cartRow ->
            NotificationItem(cart.value[cartRow])
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun NotificationItem(cart: Cart) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .width(100.dp)
                .fillMaxHeight(),
            painter = rememberImagePainter(data = cart.Image),
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier.padding(end = 10.dp)) {
                Text(
                    text ="Số lượng :" + cart.Quantity.toString(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Tổng giá :" + cart.Price,
                    maxLines = 3,
                    color = Color.Red,
                    textAlign = TextAlign.Justify,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Tên sản phẩm :" +cart.Name,
                    maxLines = 3,
                    color = Color.Blue,
                    textAlign = TextAlign.Justify,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text ="Mô tả sản phẩm :" + cart.Description,
                    maxLines = 3,
                    color = Color.Gray,
                    textAlign = TextAlign.Justify,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}