package com.example.proyectopoli.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectopoli.screens.fragments.content.PerfilFragment
import com.example.proyectopoli.screens.fragments.content.BotonesFragment
import com.example.proyectopoli.screens.fragments.content.FotosFragment
import com.example.proyectopoli.screens.fragments.content.VideosFragment
import com.example.proyectopoli.screens.fragments.content.WebFragment
import com.example.proyectopoli.ui.theme.ProyectoPOLITheme
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    var selectedScreen by remember { mutableStateOf("Perfil") }

    Row(modifier = Modifier.fillMaxSize()) {
        // Menú lateral
        DrawerContent(
            onItemSelected = { selectedScreen = it },
            selectedScreen = selectedScreen
        )

        // Contenido principal
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            when (selectedScreen) {
                "Perfil" -> PerfilFragment()
                "Fotos" -> FotosFragment()
                "Video" -> VideosFragment()
                "Web" -> WebFragment()
                "Botones" -> BotonesFragment()
            }
        }
    }
}

@Composable
fun DrawerContent(onItemSelected: (String) -> Unit, selectedScreen: String) {
    val items = listOf("Perfil", "Fotos", "Video", "Web", "Botones")

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(180.dp)
            .background(Color(0xFF263238))
            .padding(8.dp)
    ) {
        Text(
            text = "Marvel",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(12.dp)
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
                    .background(if (isSelected) Color(0xFF00E676) else Color.Transparent, RoundedCornerShape(8.dp))
                    .clickable { onItemSelected(item) }
                    .padding(8.dp)
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    ProyectoPOLITheme {
        HomeScreen()
    }
}
