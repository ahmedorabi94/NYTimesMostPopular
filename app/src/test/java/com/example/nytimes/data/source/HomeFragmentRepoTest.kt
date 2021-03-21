package com.example.nytimes.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nytimes.TestCoroutineRule
import com.example.nytimes.data.api.ApiService
import com.example.nytimes.data.db.ArticleDao
import com.example.nytimes.data.model.ArticleResponse
import com.example.nytimes.data.model.Article
import com.example.nytimes.data.repo.HomeFragmentRepo
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeFragmentRepoTest {


    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    lateinit var apiService: ApiService
    @Mock
    lateinit var articleDao: ArticleDao

    private lateinit var repository: HomeFragmentRepo

    private val article1 = Article(1,1,
        "", "Abstract1", emptyList(),"","","Title1"

    )

    private val article2 = Article(
        12,11,
        "", "Abstract2", emptyList(),"","","Title2"
    )

    private val articles = listOf(article1, article2)

    private val articleRes = ArticleResponse("", 2, articles, "Success")

    @Before
    fun setup() {
        repository = HomeFragmentRepo(apiService,articleDao)
    }

    @Test
    fun `getArticles verify getArticlesResponseAsync`() {

//        runBlocking {
//            repository.getArticlesResponseFlow("123", 5, "5678")
//            verify(apiService).getArticlesResponseAsyncTwo("123", 5, "5678")
//
//        }

    }


    @Test
    fun `getArticles return empty list`() {

        val emptyList = arrayListOf<Article>()
        val response = ArticleResponse("", 0, emptyList, "Success")

//        runBlocking {
//
//            doReturn(response)
//                .`when`(apiService)
//                .getArticlesResponseAsyncTwo("123", 5, "5678")
//
//            val response2 = repository.getArticlesResponseFlow("123", 5, "5678")
//
//            verify(apiService).getArticlesResponseAsyncTwo("123", 5, "5678")
//
//
//
//          //  assertEquals(response2.results.size, 0)
//
//
//        }

    }


    @Test
    fun `getArticles return data`() {

//        runBlocking {
//
//            doReturn(articleRes)
//                .`when`(apiService)
//                .getArticlesResponseAsyncTwo("123", 5, "5678")
//
//            val response2 = repository.getArticlesResponseFlow("123", 5, "5678")
//
//            verify(apiService).getArticlesResponseAsyncTwo("123", 5, "5678")
//
//
//         //   assertEquals(response2.results.size, 2)
//
//
//        }

    }
}