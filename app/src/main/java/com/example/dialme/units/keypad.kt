package com.example.dialme.units

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dialme.R
import com.example.dialme.call.makeCall
import com.example.dialme.resources.CallStatus
import com.example.dialme.resources.getDateAndTimeInMillis
import com.example.dialme.resources.list_of_keys
import com.example.dialme.roomdb.call_history_entity
import com.example.dialme.viewmodel.CallHistoryViewModel
import com.example.dialme.viewmodel.ContactViewModel
import kotlinx.coroutines.launch

@Composable
fun Keypad(
    phoneNumber : MutableState<String>,
    navController: NavController,
    contactViewModel: ContactViewModel,
    callHistoryViewModel: CallHistoryViewModel,
    onVoiceCallClick : ()->Unit,
    onVideoCallClick : ()->Unit){

    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { fullWidth -> -fullWidth } // Slide in from the left
        ),
        exit = slideOutVertically(
            targetOffsetY = { fullWidth -> -fullWidth } // Slide out to the left
        )
    ) {
        val context = LocalContext.current

        var dropBoxExpanded = remember { mutableStateOf(false) }

        var countryCode = remember { mutableStateOf("+91") }

        val coroutineScope = rememberCoroutineScope()

        Column(verticalArrangement = Arrangement.Bottom) {
            Box(modifier = Modifier.fillMaxWidth().height(60.dp)) {
                Row(modifier = Modifier.height(60.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically) {
                    CountryCodeDropMenu(dropBoxExpanded = dropBoxExpanded,
                        countryCode = countryCode)

                    Text(
                        countryCode.value,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .width(64.dp)
                            .height(28.dp)
                            .border(BorderStroke(1.dp, Color.White), RoundedCornerShape(100))
                            .clip(RoundedCornerShape(100))
                            .padding(top = 2.dp)
                            .clickable {
                            dropBoxExpanded.value = true
                        }
                    )
                }
                Row(modifier = Modifier.fillMaxWidth().height(60.dp).padding(start = 44.dp, end = 4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(text = phoneNumber.value , fontSize = 40.sp, fontWeight = FontWeight.Bold)
                }
                Row(modifier = Modifier.fillMaxWidth().height(60.dp).padding(end = 4.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically) {
                    if (phoneNumber.value.isNotEmpty()) {
                        IconButton(onClick = {
                            phoneNumber.value = phoneNumber.value.dropLast(1)
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                                ""
                            )
                        }
                    }else{
                        Unit
                    }
                }
            }
            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 44.dp),
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.Bottom
            ) {
                items(list_of_keys) {
                    RoundButton(symbol = it, onClick = {
                        if(phoneNumber.value.length <= 9){
                            phoneNumber.value += it
                        }else{
                            Toast.makeText(context,"Invalid entry !",Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }

            Row(
                modifier = Modifier.height(100.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(painter = painterResource(R.drawable.round_videocam_24), "",
                    modifier = Modifier.clip(RoundedCornerShape(100)).size(60.dp).clickable { })

                Icon(painter = painterResource(R.drawable.baseline_call_24), "",
                    modifier = Modifier.clip(RoundedCornerShape(100)).size(60.dp).clickable {
                        if(phoneNumber.value.length==10){
                                coroutineScope.launch {
                                    val contact = contactViewModel.getContactByNumber(number = phoneNumber.value)
                                    //called existing contact
                                    if (contact != null) {
                                        callHistoryViewModel.insertCall(call_history_entity(name = contact.name, number = contact.number, lastStatus = CallStatus.callMissed, lastCall = getDateAndTimeInMillis()))
                                    }
                                    //called new contact
                                    else{
                                        callHistoryViewModel.insertCall(call_history_entity(name = "+91" + phoneNumber.value, number = phoneNumber.value, lastStatus = CallStatus.callMissed, lastCall = getDateAndTimeInMillis()))
                                    }
                                }
                            Toast.makeText(context,"Calling...",Toast.LENGTH_SHORT).show()
                            makeCall(context = context, phoneNumber = phoneNumber.value)
                        }else{
                            Toast.makeText(context,"Dial correct number !",Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }
}