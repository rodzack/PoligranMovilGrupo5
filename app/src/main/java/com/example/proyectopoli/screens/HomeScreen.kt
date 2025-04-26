package com.example.proyectopoli.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectopoli.screens.fragments.content.*
import com.example.proyectopoli.ui.components.MenuFragment
import com.example.proyectopoli.ui.theme.ProyectoPOLITheme

@Composable
fun HomeScreen() {
    var selectedScreen by remember { mutableStateOf("Inicio") }

    Row(modifier = Modifier.fillMaxSize()) {
        MenuFragment(
            onItemSelected = { selectedScreen = it },
            selectedScreen = selectedScreen
        )

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
                else -> HomeContent()
            }
        }
    }
}

@Composable
fun HomeContent() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🍲",
            fontSize = 64.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Subgrupo 5",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Bienvenido a la app de recetas.",
            fontSize = 16.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Lista de integrantes
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Integrantes:",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            val integrantes = listOf(
                "Luisa Milena Diaz Burgos",
                "María Fernanda Diaz Burgos",
                "Juan Diego Escobar Londoño"
            )
            integrantes.forEach { nombre ->
                Text(
                    text = "🧑‍🍳 $nombre",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ProyectoPOLITheme {
        HomeScreen()
    }
}