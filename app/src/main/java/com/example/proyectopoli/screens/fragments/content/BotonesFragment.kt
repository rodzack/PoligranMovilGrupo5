package com.example.proyectopoli.screens.fragments.content

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.proyectopoli.ui.theme.ProyectoPOLITheme
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BotonesFragment() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Botones",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "En el fragmento de Fotos están disponibles dos botones:",
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        BotonOpcion(icon = "🔗", texto = "Compartir")
        BotonOpcion(icon = "⭐", texto = "Añadir a favoritos")
    }
}

@Composable
fun BotonOpcion(icon: String, texto: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = icon, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = texto, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun BotonesFragmentPreview() {
    ProyectoPOLITheme {
        BotonesFragment()
    }
}
