package com.example.nytimes.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nytimes.TestCoroutineRule
import com.example.nytimes.data.api.ApiService
import com.example.nytimes.data.api.Resource
import com.example.nytimes.data.model.ArticleResponse
import com.example.nytimes.data.model.Result
import com.example.nytimes.data.repo.HomeFragmentRepo
import com.example.nytimes.ui.viewmodels.HomeViewModel
import com.nhaarman.mockitokotlin2.doReturn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
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
    //mock(Observer::class.java) as Observer<Resource<ArticleResponse>>


    /// @Mock
    lateinit var apiService: ApiService

    @Mock
    private lateinit var repository: HomeFragmentRepo

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        //    MockitoAnnotations.initMocks(this)
        //  MockKAnnotations.init(this)

        //  apiService = mock(ApiService::class.java)
        //  repository = mock(HomeFragmentRepo(apiService)::class.java)
        //  apiUsersObserver = mock(Observer<>::class.java)

        //  apiUsersObserver = mock(Observer::class.java)

        //  repository = mock(HomeFragmentRepo::class.java)
        viewModel = HomeViewModel(repository)

    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {


        //  testCoroutineRule.runBlockingTest {

        val emptyList = arrayListOf<Result>()

        runBlocking {

            doReturn(ArticleResponse("", 0, emptyList, "Success"))
                .`when`(repository)
                .getArticlesResponse("123", 5, "5678")

            viewModel.getArticles("123", 5, "5678").observeForever(apiUsersObserver)
            verify(repository).getArticlesResponse("123", 5, "5678")
            verify(apiUsersObserver).onChanged(
                Resource.success(
                    ArticleResponse(
                        "",
                        0,
                        emptyList(),
                        "Success"
                    )
                )
            )
//            viewModel.articleResponse.removeObserver(apiUsersObserver)
//
//
//            viewModel.getArticles("123", 5, "5678").observeForever(apiUsersObserver)
//            whenever(repository.getArticlesResponse("123", 5, "5678")).thenAnswer {
//                //  Result.Success(emptyList)
//                Resource.success(ArticleResponse("", 0, emptyList, "Success"))
//            }
//            viewModel.loadData()
            //   assertNotNull(viewModel.articleResponse.value)
//            assertEquals(
//                Resource.success(ArticleResponse("", 0, emptyList, "Success")),
//                viewModel.getArticles("123", 5, "5678").value
//            )


        }


//        viewModel.getArticles("123", 5, "5678")
//        coVerify {
//            repository.getArticlesResponse("123", 5, "5678")
//        }


//            doReturn(emptyList<Result>())
//                .`when`(repository)
//                .getArticlesResponse("123",5,"5678")
//            viewModel.getArticles().observeForever(apiUsersObserver)
//            verify(repository).getArticlesResponse("123",5,"5678")
//            verify(apiUsersObserver).onChanged(
//                Resource.success(
//                    ArticleResponse(
//                        "",
//                        0,
//                        emptyList(),
//                        "Success"
//                    )
//                )
//            )
//            viewModel.getArticles().removeObserver(apiUsersObserver)
        //  }


    }


//
//    @Test
//    fun givenServerResponseError_whenFetch_shouldReturnError() {
//        testCoroutineRule.runBlockingTest {
//            val errorMessage = "Error Message For You"
//            doThrow(RuntimeException(errorMessage))
//                .`when`(dataSource)
//                .getArticlesResponse()
//            val viewModel = HomeViewModel(dataSource)
//            viewModel.getArticles().observeForever(apiUsersObserver)
//            verify(dataSource).getArticlesResponse()
//            verify(apiUsersObserver).onChanged(
//                Resource.error(
//                    RuntimeException(errorMessage).toString(),
//                    null
//                )
//            )
//            viewModel.getArticles().removeObserver(apiUsersObserver)
//        }
//    }


}