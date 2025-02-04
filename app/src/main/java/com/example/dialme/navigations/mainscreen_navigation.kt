package com.example.dialme.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dialme.resources.Routes
import com.example.dialme.screens.Contact_Screen
import com.example.dialme.screens.Keypad_Screen
import com.example.dialme.screens.Recent_Screen

@Composable
fun MainScreen_Navigation(navController: NavHostController){

    NavHost(navController = navController, startDestination = Routes.contactScreen){
        composable(Routes.keypadScreen){
            Keypad_Screen(navController = navController)
        }
        composable(Routes.recentScreen){
            Recent_Screen(navController = navController)
        }
        composable(Routes.contactScreen){
            Contact_Screen(navController = navController)
        }
    }

}