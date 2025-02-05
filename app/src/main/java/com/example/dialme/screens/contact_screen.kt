package com.example.dialme.screens

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dialme.R
import com.example.dialme.roomdb.contact_entity
import com.example.dialme.units.ContactItem
import com.example.dialme.viewmodel.CallHistoryViewModel
import com.example.dialme.viewmodel.ContactViewModel

@Composable
fun Contact_Screen(navController: NavController,
                   contactViewModel: ContactViewModel,
                   callHistoryViewModel: CallHistoryViewModel
) {

    val favouriteList = contactViewModel.favouriteContacts.collectAsState(initial = emptyList())
    val contactList = contactViewModel.allContacts.collectAsState(initial = emptyList())


    var showFavourite by remember { mutableStateOf(true) }
    var showContact by remember { mutableStateOf(false) }


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

                Row(modifier = Modifier.padding(12.dp)
                    .height(28.dp)
                    .clickable {
                        favourite = !favourite
                    },
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Mark as favourite : ")
                    val isFavourite = if(favourite){
                        R.drawable.baseline_star_24
                    }else{
                        R.drawable.baseline_star_purple500_24
                    }
                    Icon(painter = painterResource(isFavourite),"Favourite")
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        name = ""
                        number = ""
                        favourite = false
                    }) {
                        Text(text = "Clear")
                    }
                    Button(onClick = {
                        if (name.isNotEmpty() || number.isNotEmpty()){
                            contactViewModel.insertContact(contact_entity(name = name, number = number, favourite = favourite))
                            name = ""
                            number = ""
                            favourite = false
                        }else{
                            Log.d("Adding failed","Name is empty")
                        }
                    }) {
                        Text(text = "Add")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(32.dp))

        Row(modifier = Modifier.clickable {
            showFavourite = !showFavourite
            showContact = false
        }) {
            Text(text = "Favourite", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            val showingFavouriteList = if (!showFavourite) R.drawable.baseline_keyboard_arrow_right_24 else R.drawable.baseline_keyboard_arrow_down_24
            Icon(painter = painterResource(showingFavouriteList),"showlist")
        }

        if (showFavourite) {
            LazyColumn {
                items(favouriteList.value) {
                    ContactItem(contact = it, onClick = {})
                }
            }
        }

        Row(modifier = Modifier
            .padding(top = 8.dp)
            .clickable {
                showContact = !showContact
                showFavourite = false
            }
        ) {
            Text(text = "Contacts", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            val showingContactList = if (!showContact) R.drawable.baseline_keyboard_arrow_right_24 else R.drawable.baseline_keyboard_arrow_down_24
            Icon(painter = painterResource(showingContactList),"showlist")
        }
        if (showContact) {
            LazyColumn {
                items(contactList.value) {
                    ContactItem(contact = it, onClick = {})
                }
            }
        }
    }
}