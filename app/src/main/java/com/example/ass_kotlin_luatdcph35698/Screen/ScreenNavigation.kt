package com.example.ass_kotlin_luatdcph35698.Screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ass_kotlin_luatdcph35698.AddressScreen
import com.example.ass_kotlin_luatdcph35698.FurnitureApp
import com.example.ass_kotlin_luatdcph35698.LoginScreen
import com.example.ass_kotlin_luatdcph35698.ScreenSingUp
import com.example.ass_kotlin_luatdcph35698.SelectPaymentScreen
import com.example.ass_kotlin_luatdcph35698.congrat
import com.example.ass_kotlin_luatdcph35698.settingScreens

@Composable
fun ScreenNavigation() {
    var navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Screen.login.route)
    {
        composable(Screen.login.route){ LoginScreen(navController) }
        composable(Screen.HomeScreen.route){ FurnitureApp(navController) }
        composable(Screen.Singup.route) { ScreenSingUp(navController) }
        composable(Screen.Congrats.route) { congrat(navController) }
        composable(Screen.PayMentMethod.route){SelectPaymentScreen(navController)}
        composable(Screen.SettingScreen.route){settingScreens(navController)}
        composable(Screen.ShipmentScreen.route){AddressScreen(navController)}
    }
}