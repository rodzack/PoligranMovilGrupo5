package com.example.proyectopoli.screens.fragments.content

import android.content.Context
import android.media.AudioManager
import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.proyectopoli.R
import com.example.proyectopoli.ui.theme.ProyectoPOLITheme
import androidx.core.net.toUri

data class VideoItem(val title: String, val videoResId: Int, val imageResId: Int)

val videoList = listOf(
    VideoItem("Ensalada", R.raw.video1, R.drawable.ensalada),
    VideoItem("Tarta", R.raw.video2, R.drawable.tarta),
    VideoItem("Pollo", R.raw.video3, R.drawable.pollo),
    VideoItem("Sancocho", R.raw.video4, R.drawable.sancocho),
)

@Composable
fun VideosFragment() {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var selectedVideo by remember { mutableStateOf<VideoItem?>(null) }
    var isPlaying by remember { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf(false) }
    var currentVideoIndex by remember { mutableIntStateOf(-1) }
    val filteredVideos = videoList.filter { it.title.contains(searchQuery.text, ignoreCase = true) }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Video Recetas",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(8.dp)
        )
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Ej: Sancocho") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        if (selectedVideo != null) {
            currentVideoIndex = videoList.indexOf(selectedVideo)
                Dialog(
                    onDismissRequest = {
                        selectedVideo = null
                        showDialog = false
                    },
                    content = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.5f)
                                .background(Color.Black.copy(alpha = 0.8f)),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            VideoPlayer(
                                videoItem = selectedVideo!!,
                                isPlaying = isPlaying,
                                onPlayPauseToggle = { isPlaying = !isPlaying },
                                onClose = {
                                    selectedVideo = null
                                    showDialog = false
                                },
                                onNext = {
                                    currentVideoIndex = (currentVideoIndex + 1) % videoList.size
                                    selectedVideo = videoList[currentVideoIndex]
                                    isPlaying = true
                                },
                                onPrevious = {
                                    currentVideoIndex = (currentVideoIndex - 1 + videoList.size) % videoList.size
                                    selectedVideo = videoList[currentVideoIndex]
                                    isPlaying = true
                                },
                            )
                            IconButton(onClick = { selectedVideo = null; showDialog = false }) {
                                Icon(Icons.Filled.Close, contentDescription = "Cerrar", tint = Color.White)
                            }
                        }
                    },
                    properties = DialogProperties(usePlatformDefaultWidth = false)
                )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(filteredVideos) { video ->
                    VideoCard(video = video) { selectedVideo = it }
                }
            }
        }
    }
}

@Composable
fun VideoCard(video: VideoItem, onVideoClick: (VideoItem) -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onVideoClick(video) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = video.imageResId),
                contentDescription = "Imagen de ${video.title}",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Text(
                text = video.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun VideoPlayer(
    videoItem: VideoItem,
    isPlaying: Boolean,
    onPlayPauseToggle: () -> Unit,
    onClose: () -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var videoViewRef by remember { mutableStateOf<VideoView?>(null) }
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    var volume by remember { mutableFloatStateOf(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat() / audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)) }
    var duration by remember { mutableIntStateOf(0) }

    LaunchedEffect(videoItem.videoResId) {
        videoViewRef?.apply {
            stopPlayback()
            setVideoURI("android.resource://${context.packageName}/${videoItem.videoResId}".toUri())
            setOnPreparedListener { mp ->
                duration = mp.duration / 1000
                mp.start()
            }
            setOnErrorListener { _, _, _ -> true }
        }
    }

    LaunchedEffect(isPlaying) {
        videoViewRef?.let { vv ->
            if (isPlaying) {
                if (!vv.isPlaying) vv.start()
            } else {
                if (vv.isPlaying) vv.pause()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.TopEnd
        ) {
            AndroidView(
                factory = {
                    VideoView(context).apply {
                        videoViewRef = this
                        setVideoURI("android.resource://${context.packageName}/${videoItem.videoResId}".toUri())
                        setOnPreparedListener { mp ->
                            duration = mp.duration / 1000
                            if (isPlaying) mp.start()
                        }
                        setOnErrorListener { _, _, _ -> true }
                        lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                            override fun onPause(owner: LifecycleOwner) {
                                pause()
                            }

                            override fun onResume(owner: LifecycleOwner) {
                                if (isPlaying) start()
                            }

                            override fun onDestroy(owner: LifecycleOwner) {
                                stopPlayback()
                            }
                        })
                    }
                },
                update = {},
                modifier = Modifier.fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.6f))
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onPrevious) {
                    Icon(Icons.Filled.FastRewind, contentDescription = "Anterior", tint = Color.White)
                }
                IconButton(onClick = onPlayPauseToggle) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                        contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                        tint = Color.White
                    )
                }
                IconButton(onClick = onNext) {
                    Icon(Icons.Filled.FastForward, contentDescription = "Siguiente", tint = Color.White)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.VolumeUp, contentDescription = "Volumen", tint = Color.White)
                    Slider(
                        value = volume,
                        onValueChange = {
                            volume = it
                            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (it * audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)).toInt(), 0)
                        },
                        valueRange = 0f..1f,
                        modifier = Modifier.width(100.dp),
                        colors = SliderDefaults.colors(
                            thumbColor = Color.White,
                            activeTrackColor = Color.White,
                            inactiveTrackColor = Color.Gray
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VideosFragmentPreview() {
    ProyectoPOLITheme {
        VideosFragment()
    }
}