package com.example.dialme.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dialme.R
import com.example.dialme.navigations.MainScreen_Navigation
import com.example.dialme.resources.Routes

@Composable
fun MainScreen(modifier: Modifier){

    // Get the screen width and height in pixels
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val navController = rememberNavController()

    val  onScreen  = navController.currentBackStackEntryAsState().value?.destination?.route


    Log.d("Padding","$screenWidth $screenHeight")

    //Top app bar
    Column(modifier = modifier.then(Modifier.fillMaxSize())) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.06f)
            .padding(horizontal = screenWidth * 0.04f)
//            .background(Color.Gray)
            ,
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically) {

            Box(modifier = Modifier){
                Icon(painter = painterResource(R.drawable.baseline_search_24),
                    contentDescription = "Search",
                    tint = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(100))
                        .size(screenHeight * 0.036f)
                        .clickable {

                        }
                )
            }

            Spacer(Modifier.width(screenWidth * 0.04f))

            Box(modifier = Modifier){
                Icon(painter = painterResource(R.drawable.baseline_star_purple500_24),
                    contentDescription = "Favourites",
                    tint = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(100))
                        .size(screenHeight * 0.036f)
                        .clickable {

                        }
                )
            }
        }
    }

    //Middle Screen
    Column(modifier = modifier
        .then(
            Modifier
            .fillMaxSize()
            .padding(vertical = screenHeight * 0.06f)
//            .background(Color.Cyan)
//            .clickable {
//
//            }
        )
    ) {
        MainScreen_Navigation(navController = navController)
    }

    //Bottom app bar
    Column(modifier = modifier.then(Modifier.fillMaxSize())) {
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.06f)
            .padding(horizontal = screenWidth * 0.06f)
//            .background(Color.Gray)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier
                .height(screenHeight * 0.052f)
                .clip(RoundedCornerShape(40.dp))
                .width(screenWidth * 0.28f)
//                .background(Color.Cyan)
                .clickable {
                    navController.navigate(Routes.keypadScreen)
                },
                contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Keypad",
                        fontWeight = if (onScreen == Routes.keypadScreen) FontWeight.Bold else FontWeight.Normal
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100))
                            .background(if(onScreen == Routes.keypadScreen) Color.White else Color.Transparent)
                            .height(4.dp)
                            .width(screenWidth * 0.16f)
                    ) {}
                }
            }
            Box(modifier = Modifier
                .height(screenHeight * 0.052f)
                .clip(RoundedCornerShape(40.dp))
                .width(screenWidth * 0.28f)
//                .background(Color.Cyan)
                .clickable {
                    navController.navigate(Routes.recentScreen)
                    Log.d("onScreen","$onScreen")
                },
                contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Recents",
                        fontWeight = if(onScreen==Routes.recentScreen) FontWeight.Bold else FontWeight.Normal)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100))
                            .background(if(onScreen==Routes.recentScreen) Color.White else Color.Transparent)
                            .height(4.dp)
                            .width(screenWidth * 0.16f)
                    ) {}
                }
            }
            Box(modifier = Modifier
                .height(screenHeight * 0.052f)
                .clip(RoundedCornerShape(40.dp))
                .width(screenWidth * 0.28f)
//                .background(Color.Cyan)
                .clickable {
                    navController.navigate(Routes.contactScreen)
                },
                contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Contacts",
                        fontWeight = if(onScreen == Routes.contactScreen) FontWeight.Bold else FontWeight.Normal)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100))
                            .background(if(onScreen == Routes.contactScreen) Color.White else Color.Transparent)
                            .height(4.dp)
                            .width(screenWidth * 0.16f)
                    )
                }
            }
        }
    }

}