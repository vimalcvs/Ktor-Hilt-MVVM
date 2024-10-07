package com.vimalcvs.myapplication.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vimalcvs.myapplication.data.ModelPost
import com.vimalcvs.myapplication.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            try {
                val posts = repository.getPosts()
                _uiState.value = UiState.SuccessPost(posts)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("No network connection")
            }
        }
    }
}


sealed class UiState {
    data object Loading : UiState()
    data class SuccessPost(val posts: List<ModelPost>) : UiState()
    data class Error(val message: String) : UiState()
}