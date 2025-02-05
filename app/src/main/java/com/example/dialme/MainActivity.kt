package com.example.dialme

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.dialme.screens.MainScreen
import com.example.dialme.ui.theme.DialMeTheme
import com.example.dialme.viewmodel.CallHistoryViewModel
import com.example.dialme.viewmodel.ContactViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val contactViewModel : ContactViewModel by viewModels()
        val callHistoryViewModel : CallHistoryViewModel by viewModels()
        setContent {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CALL_LOG),100)
            }


            DialMeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding),
                        contactViewModel = contactViewModel,
                        callHistoryViewModel = callHistoryViewModel)
                }
            }
        }
    }
}


