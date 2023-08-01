package com.example.instantsystemtechnicaltest.data.newslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.instantsystemtechnicaltest.data.model.Article
import com.example.instantsystemtechnicaltest.data.repositories.NewsRepository
import com.example.instantsystemtechnicaltest.newslist.NewsListViewModel
import com.example.instantsystemtechnicaltest.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewsListViewModelTest {

    private lateinit var viewModel: NewsListViewModel
    private val mockNewsRepository = mockk<NewsRepository>()

    @JvmField
    @Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()


    }

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)

        coEvery { mockNewsRepository.getNewsData(any()) } returns Resource.Success(
            data = listOf(
                article
            )
        )
        viewModel = NewsListViewModel(mockNewsRepository)


    }
    @Test
    fun onInitialiseShouldReturnNewsList() = runTest {
        val expectedResult = listOf(article)
        val result = viewModel.newsList.value
        assertEquals(expectedResult, result)
    }

    @Test
    fun onInitializeShouldReturnNullSelectedArticle() = runTest {
        assertNull(viewModel.selectedArticle.value)
    }

    @Test
    fun whenSetSelectedArticleShouldSetTheArticleValue() = runTest {
        val expectedResult = article
        viewModel.setSelectedArticle(expectedResult)
        assertEquals(viewModel.selectedArticle.value, expectedResult)
    }


    private val article =
        Article(
            author = "author",
            content = "content",
            description = "description",
            publishedAt = "publishedAt",
            source = null,
            title = "title",
            url = "url",
            urlToImage = "urlToImage"

        )
}
