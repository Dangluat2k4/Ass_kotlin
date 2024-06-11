package com.example.ass_kotlin_luatdcph35698

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun congrat(navController: NavHostController) {
    var context = LocalContext.current
    var userName by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painterResource(id = R.drawable.image1),
            contentDescription ="nobody",
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
        Text(
            text = "Success ",
            modifier = Modifier, fontSize = 40.sp, fontFamily = FontFamily.Cursive
        )
        Spacer(modifier = Modifier.height(20.dp))
        Image(painterResource(id = R.drawable.image1), contentDescription = "jfhudjf")

        Spacer(modifier = Modifier.height(20.dp))



        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your order will be delivered soon. Thank you for choosing our app!",
            modifier = Modifier.
            align(Alignment.CenterHorizontally)
            ,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (userName.isNotBlank() && passWord.isNotBlank()) {
                    Toast.makeText(context, "Login successful", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        context,
                        "Please enter username and password",
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = CutCornerShape(5.dp),
        ) {
            Text(text = "Track your orders")
        }


        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (userName.isNotBlank() && passWord.isNotBlank()) {
                    Toast.makeText(context, "Login successful", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        context,
                        "Please enter username and password",
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            ),
            shape = CutCornerShape(5.dp),
        ) {
            Text(text = "BACK TO HOME")
        }
    }
}