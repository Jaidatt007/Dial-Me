package com.example.dialme.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.dialme.roomdb.contact_entity
import com.example.dialme.units.ContactItem
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dialme.units.Keypad
import com.example.dialme.viewmodel.CallHistoryViewModel
import com.example.dialme.viewmodel.ContactViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun Keypad_Screen(navController: NavController,
                  contactViewModel: ContactViewModel,
                  callHistoryViewModel: CallHistoryViewModel
){



    var phoneNumber = remember { mutableStateOf("") }

    val searchResultsFlow = if (phoneNumber.value.isNotEmpty()) {
        contactViewModel.searchContactsByNumber(phoneNumber.value)
    } else {
        flowOf(emptyList()) // Returns an empty Flow<List<contact_entity>>
    }

    val suggestionContacts by searchResultsFlow.collectAsState(initial = emptyList()) //  Call collectAsState() properly


    Column(modifier = Modifier.height(144.dp).fillMaxSize()) {
        LazyColumn {
            items(suggestionContacts){
                Log.d("Suggestion","$it")
                if(it.number != "") {
                    ContactItem(contact = it,
                        onClick = {
                            phoneNumber.value = it.number
                        }
                    )
                }
            }
        }
    }


    Column(modifier = Modifier.fillMaxSize()){
        Keypad(phoneNumber = phoneNumber,
            navController = navController,
            contactViewModel = contactViewModel,
            callHistoryViewModel = callHistoryViewModel,
            onVideoCallClick = {

            },
            onVoiceCallClick = {

            }
        )
    }

}