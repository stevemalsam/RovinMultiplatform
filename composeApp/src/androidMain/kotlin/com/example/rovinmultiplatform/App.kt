package com.example.rovinmultiplatform

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest.Builder
import coil3.request.crossfade
import com.example.rovinmultiplatform.core.model.Photo
import com.example.rovinmultiplatform.theme.AppTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(
    ExperimentalMaterial3Api::class
)

@Composable
@Preview
fun App() {
    val viewModel = koinViewModel<MarsPhotoViewModel>()
    val state by remember { viewModel.state }
    val coroutineScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    val pullToRefreshState = rememberPullToRefreshState()

    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Mars Photos",
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                )
            }
        ) { padding ->
            PullToRefreshBox(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                state = pullToRefreshState,
                isRefreshing = isRefreshing,
                onRefresh = {
                    isRefreshing = true
                    coroutineScope.launch {
                        viewModel.loadPhotos()
                        isRefreshing = false
                    }
                }
            ) {
                if (state.isLoading && !isRefreshing) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text("Loading...", style = MaterialTheme.typography.bodyLarge)
                    }
                } else {
                    LazyColumn {
                        items(state.photos) { photo: Photo ->
                            PhotoCard(photo, Modifier.padding(8.dp))
                            HorizontalDivider()
                        }


                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PhotoCard(photo: Photo,
              modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        NetworkImage(
            imageUrl = photo.imageURL,
            modifier = Modifier.aspectRatio(3f/2f),
            contentScale = ContentScale.Crop
        )
        Text("${photo.id}",
            style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text("${photo.earthDate}",
            style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun NetworkImage(imageUrl: String,
                 modifier: Modifier = Modifier,
                 contentDescription: String? = null,
                 contentScale: ContentScale = ContentScale.Crop,
                 alignment: Alignment = Alignment.Center,) {
    val request = Builder(LocalPlatformContext.current).apply {
        data(imageUrl)
        crossfade(true)
    }.build()

    AsyncImage(
        model = request,
        modifier = modifier,
        contentDescription = contentDescription,
        contentScale = contentScale,
        alignment = alignment,
        onState = { state ->
            when (state) {
                is AsyncImagePainter.State.Loading -> {
//                    Logger.d { "Image loading" }
                    Log.d("NetworkImage", "Image Loading")
                }

                is AsyncImagePainter.State.Error -> {
//                    Logger.e(state.result.throwable) { "Image loading failed" }
                    Log.e("NetworkImage", "Image Loading Failed: $state.result.throwable")
                }

                is AsyncImagePainter.State.Success -> {
//                    Logger.d { "Image loaded successfully" }
                    Log.d("NetworkImage", "Image Loaded Successfully")
                }

                is AsyncImagePainter.State.Empty -> Unit
            }
        }
    )
}