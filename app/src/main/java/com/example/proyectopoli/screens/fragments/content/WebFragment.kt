package com.example.proyectopoli.screens.fragments.content

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebFragment() {
    var text by remember { mutableStateOf(TextFieldValue("https://www.google.com")) }
    var urlToLoad by remember { mutableStateOf("https://www.google.com") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Ingrese la URL") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    urlToLoad = if (text.text.startsWith("https://")) {
                        text.text.trim()
                    } else {
                        "https://${text.text.trim().replace("http", "")}"
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Cargar")
            }

            Divider(
                thickness = 5.dp,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )

            AndroidView(
                factory = {
                    WebView(context).apply {}
                },
                update = { webView ->
                    if (urlToLoad.isNotEmpty()) {
                        webView.loadUrl(urlToLoad)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}
