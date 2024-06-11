package com.example.ass_kotlin_luatdcph35698

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.ass_kotlin_luatdcph35698.Model.Account
import com.example.ass_kotlin_luatdcph35698.Retrofit.AccountRetrofit
import com.example.ass_kotlin_luatdcph35698.Screen.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ScreenSingUp(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(id = R.drawable.banner),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Welcome",
            modifier = Modifier.align(Alignment.Start),
            fontSize = 40.sp
        )
        FormSingUp(navController)
    }
}

@Composable
fun FormSingUp(navController: NavHostController) {
    val context = LocalContext.current
    var userName by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }
    var confirmPassWord by remember { mutableStateOf("") }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Form start
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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

            OutlinedTextField(
                value = confirmPassWord,
                onValueChange = { confirmPassWord = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Confirm Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))


            // Form end

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (userName.isNotBlank() && passWord.isNotBlank() && passWord == confirmPassWord) {
                        val newAccount = Account(
                            PassWord = passWord,
                            FullName = userName,
                            Avatar = image
                        )
                        CoroutineScope(Dispatchers.IO).launch {
                            val response = AccountRetrofit().accountService.addAccount(newAccount)
                            withContext(Dispatchers.Main) {
                                if (response.isSuccessful) {
                                    Toast.makeText(
                                        context,
                                        "Account added successfully",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Failed to add account",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                }
                            }
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Please fill in all fields correctly",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                ),
                shape = CutCornerShape(5.dp),
            ) {
                Text(text = "Sign up")
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row {
                Text(text = "Already have account?", color = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))

                NextToLG(onClick = {
                    navController.navigate(Screen.login.route)
                })
            }
        }
    }
}

@Composable
fun NextToLG(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Text(
        text = "Sign in",
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        modifier = Modifier.clickable(onClick = onClick)
    )
}
