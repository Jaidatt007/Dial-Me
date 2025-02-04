package com.example.dialme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Contact_Screen(navController: NavController) {
    Column(
        modifier = Modifier.padding(start = 24.dp, end = 24.dp)
    ) {
        
        var name by remember { mutableStateOf("") }
        var number by remember { mutableStateOf("") }
        var status by remember { mutableStateOf("") }
        var lastCall by remember { mutableStateOf("") }
        var favourite by remember { mutableStateOf(false) }

        Text(text = "Add Contact", fontSize = 40.sp,
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            textAlign = TextAlign.Center)

        Box(modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8))
            .border(1.dp, Color.DarkGray, RoundedCornerShape(8))) {

            Column(modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Add New", fontSize = 24.sp)

                OutlinedTextField(modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                    value = name,
                    label = {
                        Text(text = "Name")
                    },
                    onValueChange = {
                        name = it
                    }
                )
                OutlinedTextField(modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                    value = number,
                    label = {
                        Text(text = "Number")
                    },
                    onValueChange = {
                        number = it
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {}) {
                        Text(text = "Clear")
                    }
                    Button(onClick = {}) {
                        Text(text = "Add")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(32.dp).background(Color.Gray))

        Text(text = "Favourite", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        LazyColumn {  }

        Text(text = "Contacts", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        LazyColumn {  }
    }
}