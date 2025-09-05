package com.example.rovinmultiplatform

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rovinmultiplatform.core.model.Photo
import kotlinx.coroutines.launch

class MarsPhotoViewModel(private val sdk: MarsRoverSDK) : ViewModel() {
    private val _state = mutableStateOf(MarsPhotosScreenState())
    val state: State<MarsPhotosScreenState> = _state

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, photos = emptyList())
            try {
                val photos = sdk.getPhotos(1000, 1)
                _state.value = _state.value.copy(isLoading = false, photos = photos)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, photos = emptyList())
            }
        }
    }
}

data class MarsPhotosScreenState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = emptyList()
)