package com.example.dialme

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.dialme.ui.theme.DialMeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DialMeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier){

    // Get the screen width and height in pixels
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    Log.d("Padding","$screenWidth $screenHeight")
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

                },
                contentAlignment = Alignment.Center) {
                Text(text = "Keypad")
            }
            Box(modifier = Modifier
                .height(screenHeight * 0.052f)
                .clip(RoundedCornerShape(40.dp))
                .width(screenWidth * 0.28f)
//                .background(Color.Cyan)
                .clickable {

                },
                contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Recents")
                    HorizontalDivider(modifier = Modifier.width(screenWidth * 0.2f),
                        thickness = 3.dp)
                }
            }
            Box(modifier = Modifier
                .height(screenHeight * 0.052f)
                .clip(RoundedCornerShape(40.dp))
                .width(screenWidth * 0.28f)
//                .background(Color.Cyan)
                .clickable {

                },
                contentAlignment = Alignment.Center) {
                Text(text = "Contacts")
            }
        }
    }

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
                        .clip(RoundedCornerShape(40.dp))
                        .size(screenHeight * 0.036f)
                        .clickable {

                        }
                )
            }

            Spacer(Modifier.width(screenWidth * 0.04f))

            Box(modifier = Modifier){
                Icon(painter = painterResource(R.drawable.baseline_menu_24),
                    contentDescription = "Search",
                    tint = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(40.dp))
                        .size(screenHeight * 0.036f)
                        .clickable {

                        }
                )
            }
        }
    }
}
