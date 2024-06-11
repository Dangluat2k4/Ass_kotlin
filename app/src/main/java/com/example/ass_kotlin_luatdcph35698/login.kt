package com.example.ass_kotlin_luatdcph35698

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ass_kotlin_luatdcph35698.Model.AccountLG
import com.example.ass_kotlin_luatdcph35698.Retrofit.AccountRetrofit
import com.example.ass_kotlin_luatdcph35698.Screen.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    var userName by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(id = R.drawable.banner),
            contentDescription = "nobody",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painterResource(id = R.drawable.img), contentDescription = "jfhudjf")

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Hello !",
            modifier = Modifier.align(Alignment.Start),
            fontSize = 40.sp,
            color = Color.LightGray
        )

        Text(
            text = "Welcome back",
            modifier = Modifier.align(Alignment.Start),
            fontSize = 40.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Form start
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFFFFFFFF))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = userName,
                    onValueChange = { userName = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "User name") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = passWord,
                    onValueChange = { passWord = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Password") },
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(16.dp))


                // Form end

                Text(
                    text = "Forget password",
                    modifier = Modifier,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(16.dp))

                Column {
                    NextToHome(onClick = {
                        if (userName.isNotBlank() && passWord.isNotBlank()) {
                            isLoading = true
                            CoroutineScope(Dispatchers.IO).launch {
                                val response =
                                    AccountRetrofit().accountService.login(
                                        AccountLG(
                                            passWord,
                                            userName
                                        )
                                    )
                                withContext(Dispatchers.Main) {
                                    isLoading = false
                                    if (response.isSuccessful && response.body()?.status == "success") {
                                        response.body()?.result?.let { account ->
                                            Preferences.saveAccount(
                                                context,
                                                account.FullName,
                                                account.PassWord
                                            )
                                        }
                                        Toast.makeText(context, "Login successful", Toast.LENGTH_LONG)
                                            .show()
                                        navController.navigate(Screen.HomeScreen.route)
                                    } else {
                                        Toast.makeText(context, "Login failed", Toast.LENGTH_LONG)
                                            .show()
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Please enter username and password",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    })
                }

                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    NextToSignup(onClick = {
                        navController.navigate(Screen.Singup.route)
                    })
                }

                if (isLoading) {
                    // Display a loading indicator if necessary
                }

            }


        }
    }

}

@Composable
fun NextToHome(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(7.dp)
            .width(300.dp)
            .height(60.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFF0329FF))
            .clickable(onClick = onClick)
            .then(modifier)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "LogIn",
                color = Color.White,
                fontWeight = FontWeight(600),
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun NextToSignup(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(7.dp, bottom = 20.dp)
            .width(300.dp)
            .height(60.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFFE0E1E7))
            .clickable(onClick = onClick)
            .then(modifier)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),  // Fill the size of the Box
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sign Up",
                color = Color.Black,
                fontWeight = FontWeight(600),
                fontSize = 18.sp
            )  // Set text color
        }
    }
}
