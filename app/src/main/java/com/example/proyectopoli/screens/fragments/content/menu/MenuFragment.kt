package com.example.proyectopoli.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectopoli.screens.fragments.content.BotonesFragment
import com.example.proyectopoli.ui.theme.ProyectoPOLITheme

@Composable
fun MenuFragment(onItemSelected: (String) -> Unit, selectedScreen: String) {
    val items = listOf("Perfil", "Fotos", "Video", "Web", "Botones")

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(135.dp)
            .background(Color(0xFF263238))
            .padding(8.dp)
    ) {
        Text(
            text = "Recetas",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(12.dp).clickable { onItemSelected("Home") }

        )

        items.forEach { item ->
            val isSelected = item == selectedScreen
            Text(
                text = item,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color.Black else Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(
                        if (isSelected) Color(0xFF00E676) else Color.Transparent,
                        RoundedCornerShape(8.dp)
                    )
                    .clickable { onItemSelected(item) }
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuFragmentPreview() {
    ProyectoPOLITheme {
        MenuFragment(
            onItemSelected = { },
            selectedScreen = "Home"
        )
    }
}
