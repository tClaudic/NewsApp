package com.example.instantsystemtechnicaltest.data.repositories

import com.example.instantsystemtechnicaltest.data.dataSource.NewsApi
import com.example.instantsystemtechnicaltest.data.model.Article
import com.example.instantsystemtechnicaltest.data.model.NewsResult
import com.example.instantsystemtechnicaltest.utils.Resource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class NewsRepositoryUnitTest {

    private lateinit var newsRepository: NewsRepository
    private val mockNewsApi = mockk<NewsApi>(relaxed = true)
    private val mockNewsResourceResult = mockk<Resource<NewsResult>>()
    private val mockNewsResult = mockk<NewsResult>()
    private val mockArticle = mockk<List<Article>>()


    @Before
    fun setup(){
        newsRepository = NewsRepositoryImpl(mockNewsApi)
    }

    @Test
    fun getNews_whenSuccess_shouldReturn_SuccessResponse()= runBlocking{
        val expectedResult = mockArticle
        coEvery { mockNewsApi.getNews(any()) } returns mockNewsResult
        every { mockNewsResult.articles } returns mockArticle
        every { mockNewsResourceResult.data?.articles} returns  expectedResult
        val result = newsRepository.getNewsData("")
        assertTrue(result is Resource.Success)
        assertEquals(expectedResult,result.data)
    }

    @Test
    fun getNews_whenError_shouldReturn_ExpectationMessage() = runBlocking{
        coEvery { mockNewsApi.getNews(any()) }.throws(Exception("An unknown error occurred"))
        val result = newsRepository.getNewsData("")
        assertTrue(result is Resource.Error)
        assertEquals("An unknown error occurred",result.message)
    }
    
}