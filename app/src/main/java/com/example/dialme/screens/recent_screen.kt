package com.example.dialme.screens

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.provider.CallLog
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.dialme.units.CallListItem
import com.example.dialme.viewmodel.CallHistoryViewModel
import com.example.dialme.viewmodel.ContactViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun Recent_Screen(navController: NavController,
                  contactViewModel: ContactViewModel,
                  callHistoryViewModel: CallHistoryViewModel
){

    val callHistoryList = callHistoryViewModel.allCallHistory.collectAsState(initial = emptyList())
    LazyColumn {
        items(callHistoryList.value){
            CallListItem(callItem = it, onClick = {})
        }
    }
}
