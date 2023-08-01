package com.example.instantsystemtechnicaltest.newslist

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
    private var deviceLanguage: String = Locale.getDefault().language
    private val _newsList = MutableStateFlow<List<Article?>>(listOf())
    val newsList: StateFlow<List<Article?>> = _newsList.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    var errorMessage = _errorMessage.asStateFlow()
    private val _selectedArticle = MutableStateFlow<Article?>(null)

    var selectedArticle = _selectedArticle.asStateFlow()

    init {
       getNewsList()
    }

    fun getNewsList(){
        viewModelScope.launch {

            when (val result = newsRepository.getNewsData(deviceLanguage)) {
                is Resource.Success -> {
                    _newsList.value = result.data ?: listOf()
                }
                is Resource.Error -> {
                    _errorMessage.value = result.message ?: "An unknown error occurred"
                }
            }
        }
    }

    fun setSelectedArticle(article: Article) {
        _selectedArticle.value = article
    }


}