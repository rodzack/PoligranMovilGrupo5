package com.example.proyectopoli.screens.fragments.content

import androidx.compose.foundation.Image
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
import com.example.proyectopoli.ui.theme.ProyectoPOLITheme
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.BorderStroke
import androidx.core.net.toUri


data class Receta(
    val nombre: String,
    val ingredientes: List<String>,
    val imagenResId: Int,
    val url: String
)

@Composable
fun FotosFragment() {
    val recetas = listOf(
        Receta(
            nombre = "Ensalada fresca",
            ingredientes = listOf("Langostino", "Lechuga", "Tomate", "Vinagre", "Aceite de oliva", "Naranja", "Aguacate", "Sal y pimienta"),
            imagenResId = R.drawable.ensalada,
            url = "https://jetextramar.com/recetas/ensalada-fresca-de-langostinos/"
        ),
        Receta(
            nombre = "Pollo al horno",
            ingredientes = listOf("Pollo", "Papas", "Romero", "Sal", "Ajo", "Aceite de oliva", "Cebolla"),
            imagenResId = R.drawable.pollo,
            url = "https://cocina-casera.com/pollo-asado-al-horno-con-patatas-y-cebolla/"
        ),
        Receta(
            nombre = "Tarta de frutas",
            ingredientes = listOf("Fresas", "Kiwi", "Durazno", "Masa de tarta", "Crema pastelera"),
            imagenResId = R.drawable.tarta,
            url = "https://lomaculinaria.com/tarta-de-frutas-frescas/"
        )
    )

    Row(modifier = Modifier.fillMaxSize()) {

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
    var esFavorito by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (esFavorito) Color(0xFFFFEBEE) else Color(0xFFF1F8E9)
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp),
        border = if (esFavorito) BorderStroke(2.dp, Color.Red) else null
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painterResource(id = receta.imagenResId),
                    contentDescription = receta.nombre,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Spacer(modifier = Modifier.width(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = {
                            val mensaje = "¡Mira esta receta deliciosa! 🍽️\n\n" +
                                    "Nombre: ${receta.nombre}\n" +
                                    "Ingredientes: ${receta.ingredientes.joinToString(", ")}\n" +
                                    "Receta completa: ${receta.url}\n"
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = "https://api.whatsapp.com/send?text=${Uri.encode(mensaje)}".toUri()
                            context.startActivity(intent)
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Compartir",
                            modifier = Modifier.size(20.dp),
                            tint = Color(0xFF388E3C)
                        )
                    }

                    IconButton(
                        onClick = {
                            esFavorito = !esFavorito
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = if (esFavorito) Icons.Filled.FavoriteBorder else Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorito",
                            modifier = Modifier.size(20.dp),
                            tint = if (esFavorito) Color.Red else Color.Gray
                        )
                    }
                }
            }

            Text(
                text = receta.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

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
