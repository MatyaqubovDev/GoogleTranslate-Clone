package dev.matyaqubov.googletranslateclone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.matyaqubov.googletranslateclone.MainViewModel
import dev.matyaqubov.googletranslateclone.data.remote.ApiService

class PostViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}