package com.example.ass_kotlin_luatdcph35698

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ass_kotlin_luatdcph35698.Model.AccountLG
import com.example.ass_kotlin_luatdcph35698.Screen.Screen
import com.example.ass_kotlin_luatdcph35698.ViewModel.AccountViewModel

@Composable
fun AccountScreenControl(navController: NavController, accountViewModel: AccountViewModel = viewModel()) {
    val context = LocalContext.current
    accountViewModel.loadAccount(context)
    val account = accountViewModel.account.observeAsState(initial = null)

    Column(modifier = Modifier.fillMaxSize()) {
        account.value?.let {
            AccountGrid(it, navController)
        } ?: run {
            Text(text = "No account logged in")
        }
    }
}

@Composable
fun AccountGrid(account: AccountLG, navController: NavController) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painterResource(id = R.drawable.image2),
            contentDescription = "Avatar",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .clip(CircleShape)
        )
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp)
        ) {
            Text(
                text = "Welcome to app!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 5.dp)
            )
            Text(
                text = "Name: ${account.PassWord}",
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
        }
    }

    Spacer(modifier = Modifier.height(30.dp))

    Column {
        MethodAccount(
            title = "Setting",
            message = "Already have 10 orders",
            click = { navController.navigate("settingScreen") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        MethodAccount(
            title = "Shipping Addresses",
            message = "03 Addresses",
            click = { navController.navigate("AddressScreen") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        MethodAccount(
            title = "Payment Method",
            message = "You have 2 cards",
            click = { navController.navigate("paymentMethod") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        MethodAccount(
            title = "Log out",
            message = "Notification, Password, FAQ, Contact",
            click = { navController.navigate("LoginScreen") }
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun MethodAccount(title: String, message: String, click: () -> Unit) {
    Row(
        modifier = Modifier
            .height(85.dp)
            .fillMaxWidth()
            .clickable(onClick = click)
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
            .background(Color("#FFFFFF".toColorInt())),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = title, fontSize = 20.sp, fontWeight = FontWeight(700)
                )
                Text(text = message, fontSize = 15.sp, color = Color.Gray)
            }
            Image(
                painter = painterResource(id = R.drawable.back), contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
