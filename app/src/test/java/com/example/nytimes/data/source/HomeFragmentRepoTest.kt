package com.example.nytimes.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nytimes.TestCoroutineRule
import com.example.nytimes.api.ApiService
import com.example.nytimes.data.model.ArticleResponse
import com.example.nytimes.data.model.Result
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

    private lateinit var repository: HomeFragmentRepo

    private val article1 = Result(
        "Abstract1", "", 0, emptyList(),
        "", "", "", "", "Title1", "", "", "", ""
    )

    private val article2 = Result(
        "Abstract2", "", 0, emptyList(),
        "", "", "", "", "Title2", "", "", "", ""
    )

    private val articles = listOf(article1, article2)

    private val articleRes = ArticleResponse("", 2, articles, "Success")

    @Before
    fun setup() {
        // MockKAnnotations.init(this)
        repository = HomeFragmentRepo(apiService)
    }

    @Test
    fun `getArticles return data`() {


        runBlocking {
            repository.getArticlesResponse("123", 5, "5678")
            verify(apiService).getArticlesResponseAsync("123", 5, "5678")

        }

    }


    @Test
    fun `getArticles return data2`() {

        val emptyList = arrayListOf<Result>()
        val response = ArticleResponse("", 2, emptyList, "Success")

        runBlocking {

            doReturn(response)
                .`when`(apiService)
                .getArticlesResponseAsync("123", 5, "5678")

            val response2 = repository.getArticlesResponse("123", 5, "5678")

            verify(apiService).getArticlesResponseAsync("123", 5, "5678")



            assertEquals(response2.num_results, response.num_results)


        }

    }
}