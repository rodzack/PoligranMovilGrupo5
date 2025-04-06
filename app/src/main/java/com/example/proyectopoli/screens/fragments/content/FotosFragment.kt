package com.example.proyectopoli.screens.fragments.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectopoli.R
import com.example.proyectopoli.ui.components.MenuFragment
import com.example.proyectopoli.ui.theme.ProyectoPOLITheme

data class Receta(
    val nombre: String,
    val ingredientes: List<String>,
    val imagenResId: Int
)

@Composable
fun FotosFragment() {
    val recetas = listOf(
        Receta(
            nombre = "Ensalada fresca",
            ingredientes = listOf("Lechuga", "Tomate", "Pepino", "Aceite de oliva", "Limón"),
            imagenResId = R.drawable.ensalada
        ),
        Receta(
            nombre = "Pollo al horno",
            ingredientes = listOf("Pechuga de pollo", "Papas", "Romero", "Sal", "Ajo", "Aceite"),
            imagenResId = R.drawable.pollo
        ),
        Receta(
            nombre = "Tarta de frutas",
            ingredientes = listOf("Fresas", "Kiwi", "Durazno", "Masa de tarta", "Crema pastelera"),
            imagenResId = R.drawable.tarta
        )
    )

    Row(modifier = Modifier.fillMaxSize()) {
        MenuFragment(onItemSelected = {}, selectedScreen = "Fotos")

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Text(
                text = "Galería recetas",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn {
                items(recetas) { receta ->
                    RecetaCard(receta)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}
@Composable
fun RecetaCard(receta: Receta) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.Top
            ) {
                // Imagen a la izquierda
                Image(
                    painter = painterResource(id = receta.imagenResId),
                    contentDescription = receta.nombre,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Íconos alineados arriba a la derecha de la imagen
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = { /* Compartir */ },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Compartir",
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    IconButton(
                        onClick = { /* Favorito */ },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Favorito",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // Nombre
            Text(
                text = receta.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Ingredientes
            Column {
                receta.ingredientes.forEach { ingrediente ->
                    Text(
                        text = "- $ingrediente",
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FotosFragmentPreview() {
    ProyectoPOLITheme {
        FotosFragment()
    }
}