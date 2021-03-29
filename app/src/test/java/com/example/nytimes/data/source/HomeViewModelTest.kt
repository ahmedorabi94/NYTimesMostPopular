package com.example.nytimes.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nytimes.TestCoroutineRule
import com.example.nytimes.data.api.ErrorResponse
import com.example.nytimes.data.api.Resource
import com.example.nytimes.data.api.ResultWrapper
import com.example.nytimes.data.model.Article
import com.example.nytimes.data.model.ArticleResponse
import com.example.nytimes.data.repo.HomeFragmentRepo
import com.example.nytimes.ui.viewmodels.HomeViewModel
import com.example.nytimes.util.AppConstants
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
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

    // A rule is a way to run code before and after the execution of a test in JUnit.
    //InstantTaskExecutorRule is a JUnit rule that configures LiveData to execute each task synchronously

    //MainCoroutineScopeRule is a custom rule in this codebase that configures Dispatchers.Main to use
    // a TestCoroutineDispatcher from kotlinx-coroutines-test. This allows tests to advance a virtual-clock for testing,
    // and allows code to use Dispatchers.Main in unit tests.

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<ArticleResponse>>

    @Mock
    private lateinit var repository: HomeFragmentRepo

    private lateinit var viewModel: HomeViewModel


    private val article1 = Article(
        1, 1,
        "", "Abstract1", emptyList(), "", "", "Title1"

    )

    private val article2 = Article(
        12, 11,
        "", "Abstract2", emptyList(), "", "", "Title2"
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

            viewModel.getArticlesFlow()

            verify(repository, times(1)).getArticlesResponseFlow(
                "all-sections",
                7,
                AppConstants.API_KEY
            )

            viewModel.articleResponse.observeForever(apiUsersObserver)
            assertNotNull(viewModel.articleResponse.value)


        }

    }

    @Test
    fun givenResponseSuccess_whenFetch_shouldReturnSuccess() {


        val flow = flow {
            emit(
                ResultWrapper.Success(articleRes)
            )
        }



        testCoroutineRule.runBlockingTest {
            doReturn(flow)
                .`when`(repository)
                .getArticlesResponseFlow("all-sections", 7, AppConstants.API_KEY)

            viewModel.articleResponse.observeForever(apiUsersObserver)
            viewModel.getArticlesFlow()
            verify(repository).getArticlesResponseFlow(
                "all-sections",
                7,
                AppConstants.API_KEY
            )

            verify(apiUsersObserver).onChanged(
                Resource.success(
                    ArticleResponse("", 2, articles, "Success")
                )
            )
            viewModel.articleResponse.removeObserver(apiUsersObserver)
        }

    }


    @Test
    fun givenResponseError_whenFetch_shouldReturnError() {


        val flow = flow {
            emit(
                ResultWrapper.Error(404, ErrorResponse(message = "404 Not Found"))
            )
        }

        testCoroutineRule.runBlockingTest {
            doReturn(flow)
                .`when`(repository)
                .getArticlesResponseFlow("all-sections", 7, AppConstants.API_KEY)

            viewModel.articleResponse.observeForever(apiUsersObserver)
            viewModel.getArticlesFlow()
            verify(repository).getArticlesResponseFlow("all-sections", 7, AppConstants.API_KEY)

            verify(apiUsersObserver).onChanged(
                Resource.error(data = null, message = "404 Not Found")
            )
            viewModel.articleResponse.removeObserver(apiUsersObserver)
        }
    }


}