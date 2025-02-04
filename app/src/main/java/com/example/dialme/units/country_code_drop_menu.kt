package com.example.dialme.units

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun CountryCodeDropMenu(dropBoxExpanded : MutableState<Boolean>,
                        countryCode : MutableState<String>){
    DropdownMenu(
        modifier = Modifier.width(80.dp),
        expanded = dropBoxExpanded.value ,
        onDismissRequest = {
            dropBoxExpanded.value = false
        },
        offset = DpOffset(4.dp, -(12).dp)
    ) {
        // First section
        DropdownMenuItem(
            text = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("+91")
                    Text(text = "\uD83C\uDDEE\uD83C\uDDF3")
                }
            },
            onClick = {
                countryCode.value = "+91"
                dropBoxExpanded.value = false
            }
        )
        DropdownMenuItem(
            text = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("+977")
                    Text(text = "\uD83C\uDDF3\uD83C\uDDF5")
                }
            },
            onClick = {
                countryCode.value = "+977"
                dropBoxExpanded.value = false
            }
        )
        DropdownMenuItem(
            text = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("+975")
                    Text(text = "\uD83C\uDDE7\uD83C\uDDF9")
                }
            },
            onClick = {
                countryCode.value = "+975"
                dropBoxExpanded.value = false
            }
        )

    }
}