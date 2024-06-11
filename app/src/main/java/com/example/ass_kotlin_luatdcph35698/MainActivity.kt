package com.example.ass_kotlin_luatdcph35698

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.ass_kotlin_luatdcph35698.Screen.ScreenNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenNavigation()
        }
    }
}

@Composable
fun FullSceneImage(){
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painterResource(id = R.drawable.image1),
            contentDescription ="nobody",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Text(text = "The best simple place where you discover most wonderful furnitures and make your home beautiful",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)

        )

        Button(onClick = { /*TODO*/ },
            shape = CutCornerShape(5.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(30.dp)
        ) {
            Text(text = "Get Start")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    var nav = rememberNavController();
    ScreenSingUp(nav)
}