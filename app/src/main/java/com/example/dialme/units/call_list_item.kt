package com.example.dialme.units

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dialme.R
import com.example.dialme.resources.CallStatus
import com.example.dialme.resources.formatMillisToDate
import com.example.dialme.resources.formatMillisToTime
import com.example.dialme.roomdb.call_history_entity
import com.example.dialme.roomdb.contact_entity

@Composable
fun CallListItem(callItem : call_history_entity,
                 onClick : () -> Unit ){
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .defaultMinSize(minHeight = 30.dp)
        .padding(top = 2.dp)
        .clickable {
            onClick()
        },
        elevation = CardDefaults.cardElevation(2.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondary.copy(alpha = 3f))
    ) {

        val context = LocalContext.current

        val callStatus = when (callItem.lastStatus) {
            CallStatus.callReceived -> R.drawable.baseline_call_24
            CallStatus.callMissed -> R.drawable.baseline_phone_missed_24
            CallStatus.callRejected -> R.drawable.baseline_do_not_disturb_24
            else -> R.drawable.baseline_phone_missed_24
        }

        Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()
            .background(MaterialTheme.colorScheme.tertiary),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(callStatus),"",
                modifier = Modifier.padding(horizontal = 8.dp).size(30.dp),
                tint = Color.Black)
            VerticalDivider(modifier = Modifier.height(32.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.3f))
            Column(modifier = Modifier.padding(start = 16.dp, end = 8.dp).fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 3.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = callItem.name, fontSize = 18.sp ,
                        fontWeight = FontWeight.ExtraBold,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = false,
                        modifier = Modifier.fillMaxWidth(0.6f).padding(top = 2.dp),
                        color = MaterialTheme.colorScheme.onTertiary,
                        maxLines = 1)

                    Text(
                        text = formatMillisToDate(callItem.lastCall), fontSize = 8.sp,
                        modifier = Modifier.fillMaxWidth(1f).padding(horizontal = 4.dp),
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.7f),
                        maxLines = 1
                    )

                }
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = callItem.number, fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 3.dp),
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                    Text(
                        text = formatMillisToTime(callItem.lastCall), fontSize = 10.sp,
                        modifier = Modifier.fillMaxWidth(1f).padding(horizontal = 4.dp),
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.7f),
                        maxLines = 1
                    )
                }
            }
        }
    }
}