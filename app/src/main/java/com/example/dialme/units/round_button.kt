package com.example.dialme.units

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoundButton(
    symbol : String,
    onClick : () -> Unit
){
    FloatingActionButton(
        modifier = Modifier.padding(16.dp).aspectRatio(1f),
        onClick = onClick,
        shape = RoundedCornerShape(100),
        containerColor = Color.Transparent,
        elevation = FloatingActionButtonDefaults.elevation(1.dp)
    ) {
        Text(
            text = symbol,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
