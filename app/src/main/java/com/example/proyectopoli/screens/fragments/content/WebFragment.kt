package com.example.proyectopoli.screens.fragments.content

import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WebFragment() {
    var text by remember { mutableStateOf(TextFieldValue("https://www.google.com")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Ingrese la URL") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        var url by remember { mutableStateOf("https://www.google.com") }

        Button(
            onClick = {
                url = if (text.text.startsWith("https://")) {
                    text.text.trim()
                } else {
                    "https://${text.text.trim().replace("http://", "")}"
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Cargar")
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .background(Color.LightGray)
        ) {
            ComponentWebView(url = url)
        }
    }
}

@Composable
fun ComponentWebView(url: String) {
    val webViewRef = remember { mutableStateOf<WebView?>(null) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { ctx ->
                WebView(ctx).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()

                    setLayerType(View.LAYER_TYPE_SOFTWARE, null)

                    isFocusable = false
                    isFocusableInTouchMode = false

                    webViewRef.value = this
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        LaunchedEffect(url) {
            delay(100) 
            webViewRef.value?.loadUrl(url)
        }
    }
}