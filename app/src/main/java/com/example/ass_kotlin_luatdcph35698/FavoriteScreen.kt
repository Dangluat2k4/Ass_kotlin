package com.example.ass_kotlin_luatdcph35698

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ass_kotlin_luatdcph35698.ViewModel.ProductViewModel


@Composable
fun FavoriteScreen() {
    Column(modifier = Modifier) {
        val movieViewModel: ProductViewModel = viewModel()
        FavoriteGird(movieViewModel = movieViewModel)
    }
}

@Composable
fun FavoriteGird(  movieViewModel: ProductViewModel = viewModel()) {
    val products = movieViewModel.product.observeAsState(initial = emptyList())

    var filteredProducts = products.value.filter { it.Status == 1 }
    Box(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(filteredProducts) { productRow ->
                FavoriteItem(productRow,movieViewModel)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, bottom = 15.dp),
                contentAlignment = Alignment.BottomEnd) {
            
        }
    }
}