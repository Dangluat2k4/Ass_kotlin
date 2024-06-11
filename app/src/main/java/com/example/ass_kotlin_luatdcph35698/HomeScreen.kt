package com.example.ass_kotlin_luatdcph35698

import android.util.Log
import android.widget.Toast
import android.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.ass_kotlin_luatdcph35698.Model.Product
import com.example.ass_kotlin_luatdcph35698.Model.ProductDT
import com.example.ass_kotlin_luatdcph35698.Screen.NotificationScreen
import com.example.ass_kotlin_luatdcph35698.ViewModel.ProductViewModel

enum class ROUTE_HOME_SCREEN {
    Home,
    Favorite,
    Notification,
    Profile
}

class Category(val icon: Int, val name: String)

@Composable
fun FurnitureApp(navHostController: NavController) {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavigationItem(ROUTE_HOME_SCREEN.Home.name, Icons.Default.Home, Icons.Outlined.Home),
        BottomNavigationItem(
            ROUTE_HOME_SCREEN.Favorite.name,
            Icons.Default.Favorite,
            Icons.Outlined.Favorite
        ),
        BottomNavigationItem(
            "Notification",
            Icons.Default.Notifications,
            Icons.Outlined.Notifications
        ),
        BottomNavigationItem(
            ROUTE_HOME_SCREEN.Profile.name,
            Icons.Default.Person,
            Icons.Outlined.Person
        )
    )
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(navController = navController, navHostController = navHostController)
            },
            bottomBar = {
                BottomNavigationBar(
                    items = items,
                    selectedItemIndex = selectedItemIndex,
                    onItemSelected = { index ->
                        selectedItemIndex = index
                        navController.navigate(items[index].title)
                    }
                )
            }
        ) { innerPadding ->
            NavigationGraph(
                navHostController = navHostController,
                navController = navController,
                innerPadding = innerPadding
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navController: NavController, navHostController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var currentRoute = navBackStackEntry?.destination?.route ?: "Home"
    val homeTitle = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Gray,
                fontSize = 18.sp,
                fontWeight = FontWeight(400),
            )
        ) {
            append("Make home \n")
        }
        withStyle(
            style = SpanStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
            )
        ) {
            append("BEAUTIFUL")
        }
    }
    val title: Any = when (currentRoute) {
        "Home" -> homeTitle
        "Favorite" -> "Favorite"
        "Notification" -> "Notification"
        "Profile" -> "Profile"
        else -> "Furniture App"
    }

    androidx.compose.material3.TopAppBar(
        title = {
            if (title is AnnotatedString) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            } else if (title is String) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        actions = {
            IconButton(onClick = { navHostController.navigate("cart") }) {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
    )
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = Color("#ffffff".toColorInt())
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = { onItemSelected(index) },
                icon = {
                    Icon(
                        imageVector = if (selectedItemIndex == index) item.selectIcon else item.unselectItem,
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = android.R.color.black),
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}

@Composable
fun CategoryItem(icon: Int, name: String) {
    Column(
        modifier = Modifier.padding(end = 25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .size(44.dp)
                .shadow(elevation = 2.dp, RoundedCornerShape(14.dp))
                .background(color = Color("#F5F5F5".toColorInt())),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = name,
            color = Color("#999999".toColorInt()),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun HomeScreen(
    movieViewModel: ProductViewModel = viewModel(),
    innerPadding: PaddingValues = PaddingValues(),
    navController: NavController
) {
    val categoryArr = listOf(
        Category(R.drawable.image1, "Chair"),
        Category(R.drawable.imagedetails, "Chair"),
        Category(R.drawable.image3, "Chair"),
        Category(R.drawable.image2, "Chair"),
        Category(R.drawable.image1, "Chair"),
        Category(R.drawable.image3, "Chair"),
        Category(R.drawable.cart, "Chair"),
        Category(R.drawable.image2, "Chair"),
        Category(R.drawable.image1, "Chair"),
        Category(R.drawable.image3, "Chair"),
        Category(R.drawable.image1, "Chair"),
        Category(R.drawable.image2, "Chair")
    )
    val products = movieViewModel.product.observeAsState(initial = emptyList())
    val scrollState = rememberScrollState()
    //   val account = movieViewModel.product.observeAsState(initial = emptyList())
    Column(
        modifier = Modifier.padding(
            top = 10.dp,
            end = 15.dp,
            start = 15.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
        ) {
            categoryArr.forEach { category ->
                CategoryItem(icon = category.icon, name = category.name)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products.value.size) { productRow ->
                ProductItem(products.value[productRow], movieViewModel)
            }
        }
    }
}


@Composable
fun NavigationGraph(
    navHostController: NavController,
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    val context = LocalContext.current
    NavHost(
        navController,
        startDestination = ROUTE_HOME_SCREEN.Home.name,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(ROUTE_HOME_SCREEN.Home.name) {
            val movieViewModel: ProductViewModel = viewModel()
            HomeScreen(movieViewModel = movieViewModel, navController = navHostController)
        }
        composable(ROUTE_HOME_SCREEN.Favorite.name) { FavoriteScreen() }
        composable(ROUTE_HOME_SCREEN.Notification.name) { NotificationScreen() }
        composable(ROUTE_HOME_SCREEN.Profile.name) { AccountScreenControl(navHostController) }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectIcon: ImageVector,
    var unselectItem: ImageVector
)


@Composable
fun ProductItem(product: ProductDT, productViewModel: ProductViewModel) {
    val context = LocalContext.current
    var showDialog by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .width(165.dp)
            .height(253.dp),
//            clickable { navController.navigate("detail") },
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { showDialog = true },
                painter = rememberImagePainter(data = product.Image),
                contentDescription = null
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp, end = 5.dp, top = 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ) {
                Row {
                }
                Row(
                    modifier = Modifier
                        .size(30.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(6.dp))
                        .background(color = Color(0xFF95A5A6))
                        .alpha(1f),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    IconButton(onClick = {
                        productViewModel.updateUnProductStatus(product._id, 1) { response ->
                            if (response.isSuccessful) {
                                productViewModel.loadProductList()
                                Toast.makeText(
                                    context,
                                    "Sản phẩm đã được thêm vào yêu thích ",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                            }
                        }
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .size(30.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(6.dp))
                        .background(color = Color(0xFF95A5A6))
                        .alpha(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {

                    IconButton(onClick = {
                        productViewModel.addToCart(product) { response ->

                            if (response.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Bạn đã đặt hàng thành công !",
                                    Toast.LENGTH_LONG
                                ).show()
                                Log.e(
                                    "ProductViewModel",
                                    "Failed to load product list: ${response.message()}"
                                )
                            } else {
                                Log.e(
                                    "ProductItem",
                                    "Failed to add product to cart: ${
                                        response.errorBody()?.string()
                                    }"
                                )
                            }

                        }
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.cart),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                }

            }
        }
        Text(
            text = product.ProductName, style = MaterialTheme.typography.bodyLarge,
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight(500),
        )
        Text(
            text = "\$ " + product.Price, style = MaterialTheme.typography.bodyLarge,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )

        if (showDialog) {
            AlertDialog(onDismissRequest = { showDialog = false },
                title = {
                    Text(text = "Product Detail")
                },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Image(
                            painter = rememberImagePainter(data = product.Image),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                        Row {
                            Text(
                                text = "Tên sản phẩm :",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = product.ProductName)
                        }

                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            Text(
                                text = "Giá :",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = product.Price)
                        }
                        Spacer(modifier = Modifier.height(5.dp))

                        Row {
                            Text(
                                text = "Mô tả :",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = product.Description)
                        }
                    }

                },
                confirmButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text("Close")
                    }
                })
        }
    }


    @Composable
    fun CartItem(icon: Int, name: String, price: Double) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon), contentDescription = null, modifier = Modifier
                    .width(110.dp)
                    .height(120.dp), contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .width(200.dp)
                    .padding(start = 10.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(600),
                        color = colorResource(
                            id = R.color.black
                        )
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(text = "\$ " + price, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                Row(
                    modifier = Modifier.width(113.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(color = Color("#E0E0E0".toColorInt())),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = null,
                            modifier = Modifier.size(13.dp)
                        )
                    }
                    Text(
                        text = "01",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(700)
                    )
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(color = Color("#E0E0E0".toColorInt())),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.apart),
                            contentDescription = null,
                            modifier = Modifier.size(13.dp)
                        )
                    }
                }


            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )

                Row {

                }
            }
        }
    }
}


@Composable
fun FavoriteItem(product: ProductDT, productViewModel: ProductViewModel) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = product.Image),
            contentDescription = null,
            modifier = Modifier
                .width(110.dp)
                .height(120.dp),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .width(200.dp)
                .padding(start = 10.dp)
                .fillMaxHeight()
        ) {
            Text(
                text = product.ProductName,
                fontSize = 15.sp,
                fontWeight = FontWeight(600),
                color = colorResource(
                    id = R.color.black
                )
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = "\$ " + product.Price, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            IconButton(onClick = {
                productViewModel.updateProductStatus(product._id, 0) { response ->
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Sản phẩm đã được loại bỏ", Toast.LENGTH_LONG)
                            .show()
                        productViewModel.loadProductList();
                    } else {

                    }
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
            }


            Row {
                IconButton(onClick = {
                    productViewModel.addToCart(product) { response ->

                        if (response.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Bạn đã đặt hàng thành công !",
                                Toast.LENGTH_LONG
                            ).show()
                            Log.e(
                                "ProductViewModel",
                                "Failed to load product list: ${response.message()}"
                            )
                        } else {
                            Log.e(
                                "ProductItem",
                                "Failed to add product to cart: ${
                                    response.errorBody()?.string()
                                }"
                            )
                        }

                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.bag),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

//IconButton(onClick = {
//    productViewModel.addToCart(product) { response ->
//
//        if (response.isSuccessful) {
//            Toast.makeText(context,"Bạn đã đặt hàng thành công !",Toast.LENGTH_LONG).show()
//            Log.e(
//                "ProductViewModel",
//                "Failed to load product list: ${response.message()}"
//            )
//        } else {
//            Log.e(
//                "ProductItem",
//                "Failed to add product to cart: ${
//                    response.errorBody()?.string()
//                }"
//            )
//        }
//
//    }
//}) {
//    Image(
//        painter = painterResource(id = R.drawable.cart),
//        contentDescription = null,
//        modifier = Modifier.size(18.dp)
//    )
//}



