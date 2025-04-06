


package com.example.proyectopoli.screens.fragments.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectopoli.ui.components.MenuFragment
import com.example.proyectopoli.ui.theme.ProyectoPOLITheme

@Composable
fun PerfilFragment() {
    Row(modifier = Modifier.fillMaxSize()) {

        MenuFragment(
            onItemSelected = { },
            selectedScreen = "Perfil"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Bienvenido",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tarjeta de información básica
            UserInfoCard()

            Spacer(modifier = Modifier.height(16.dp))

            // Preferencias dietéticas
            DietPreferencesCard()

            Spacer(modifier = Modifier.height(16.dp))

            // Alergias
            AllergiesCard()
        }
    }
}

@Composable
fun UserInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "👤",
                fontSize = 38.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Nombre: Juan Pérez", fontSize = 16.sp)
            Text(text = "Correo: juanperez@correo.com", fontSize = 16.sp)
            Text(text = "Celular: 3011234567", fontSize = 16.sp)
            Text(text = "Edad: 25", fontSize = 16.sp)
        }
    }
}

@Composable
fun DietPreferencesCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Preferencias dietéticas", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "150 Recetas favoritas", fontSize = 14.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                DietPreferenceIndicator(Color.Gray, "80", "Vegano")
                DietPreferenceIndicator(Color.LightGray, "32", "Vegetariano")
            }
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                DietPreferenceIndicator(Color.Gray, "24", "Sin gluten")
                DietPreferenceIndicator(Color.LightGray, "14", "Baja en grasas")
            }
        }
    }
}

@Composable
fun DietPreferenceIndicator(color: Color, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color, RoundedCornerShape(6.dp))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = color)
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun AllergiesCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Alergias", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Column {
                AllergyItem("Maní")
                AllergyItem("Lácteos")
                AllergyItem("Mariscos")
                AllergyItem("Gluten")
            }
        }
    }
}

@Composable
fun AllergyItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Checkbox(
            checked = true,
            onCheckedChange = null // <- importante para que funcione en Preview
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilFragmentPreview() {
    ProyectoPOLITheme {
        PerfilFragment()
    }
}