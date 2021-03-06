package com.example.nytimes.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nytimes.TestCoroutineRule
import com.example.nytimes.data.api.Resource
import com.example.nytimes.data.model.ArticleResponse
import com.example.nytimes.data.model.Result
import com.example.nytimes.data.repo.HomeFragmentRepo
import com.example.nytimes.ui.viewmodels.HomeViewModel
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<ArticleResponse>>

    @Mock
    private lateinit var repository: HomeFragmentRepo

    private lateinit var viewModel: HomeViewModel


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
    fun setUp() {
        viewModel = HomeViewModel(repository)

    }


    @Test
    fun `getArticles verify getArticlesResponse`() {

        runBlocking {
            viewModel.getArticlesTest("123", 5, "5678")
            verify(repository).getArticlesResponse("123", 5, "5678")

            viewModel.articleResponse.observeForever(apiUsersObserver)
            assertNotNull(viewModel.articleResponse.value)


        }

    }

    @Test
    fun givenResponseSuccess_whenFetch_shouldReturnSuccess() {


        testCoroutineRule.runBlockingTest {
            doReturn(articleRes)
                .`when`(repository)
                .getArticlesResponse("123", 5, "5678")

            viewModel.articleResponse.observeForever(apiUsersObserver)
            viewModel.getArticlesTest("123", 5, "5678")
            verify(repository).getArticlesResponse("123", 5, "5678")

            verify(apiUsersObserver).onChanged(
                Resource.success(
                    ArticleResponse(
                        "", 2, articles, "Success"
                    )
                )
            )
            viewModel.articleResponse.removeObserver(apiUsersObserver)
        }

    }


    @Test
    fun givenResponseError_whenFetch_shouldReturnError() {

        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Occurred!"
            doThrow(RuntimeException(errorMessage))
                .`when`(repository)
                .getArticlesResponse("123", 5, "5678")

            viewModel.articleResponse.observeForever(apiUsersObserver)
            viewModel.getArticlesTest("123", 5, "5678")
            verify(repository).getArticlesResponse("123", 5, "5678")

            verify(apiUsersObserver).onChanged(
                Resource.error(
                    RuntimeException(errorMessage).toString(),
                    null
                )
            )
            viewModel.articleResponse.removeObserver(apiUsersObserver)
        }
    }


}