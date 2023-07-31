package com.example.instantsystemtechnicaltest.newslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instantsystemtechnicaltest.data.model.Article
import com.example.instantsystemtechnicaltest.data.repositories.NewsRepository
import com.example.instantsystemtechnicaltest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    var deviceLanguage: String = Locale.getDefault().language
    private val _newsList = MutableStateFlow<List<Article?>>(listOf())
    val newsList: StateFlow<List<Article?>> = _newsList.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    var isLoading = _isLoading.asStateFlow()
    private val _errorMessage = MutableStateFlow("")
    var errorMessage = _errorMessage.asStateFlow()
    private val _selectedArticle = MutableStateFlow<Article?>(null)

    var selectedArticle = _selectedArticle.asStateFlow()

    init {
        viewModelScope.launch {
            _isLoading.value = true
            val result = newsRepository.getNewsData(deviceLanguage)
            when (result) {
                is Resource.Success -> {
                    Log.e("vmtest", result.data?.get(0)?.source?.id.toString())
                    _newsList.value = result.data ?: listOf()
                    _isLoading.value = false
                }

                is Resource.Error -> {
                    _errorMessage.value = result.message ?: ""
                    Log.e("error", errorMessage.toString())
                    _isLoading.value = false
                }
            }
        }
    }

    fun setSelectedArticle(article: Article) {
        _selectedArticle.value = article
    }


}