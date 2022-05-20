package dev.matyaqubov.googletranslateclone

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.matyaqubov.googletranslateclone.data.remote.ApiService
import dev.matyaqubov.googletranslateclone.model.TranslationResponse
import kotlinx.coroutines.launch

class MainViewModel(val apiService: ApiService) : ViewModel() {
    private val _translationWord = MutableLiveData<TranslationResponse>()
    val translationWord = _translationWord

    fun translationWord(word: String, sourse: String, target: String) = viewModelScope.launch {
        val response = apiService.translateToEng(word, sourse, target)
        _translationWord.value = response
    }
}