package com.times.sample.remote

import com.sample.core.domain.api.ApiServices
import com.sample.core.domain.entity.ItemsResponse
import com.sample.core.domain.remote.GsonProvider
import com.times.sample.utils.CoroutineTestRule
import com.times.sample.utils.MockResponseFileReader
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class PopularArticleListApiTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private var mockWebServer = MockWebServer()
    private lateinit var articleListApi: ItemsResponse

    @Before
    fun setUp() {
        mockWebServer.start()
        articleListApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                GsonConverterFactory.create(GsonProvider().instance)
            )
            .create(ApiServices::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetch articles response is successful`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("response.json").content)

        mockWebServer.enqueue(response)
        runBlocking(coroutineTestRule.testDispatcher) {
            val articleListResponse: Response<ItemsResponse> = articleListApi.fetchArticles(7, "")
            Truth.assertThat(articleListResponse.isSuccessful).isTrue()
        }
    }

    @Test
    fun `fetch articles response body has desired num_results`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("response.json").content)

        mockWebServer.enqueue(response)
        runBlocking(coroutineTestRule.testDispatcher) {
            val articleListResponse: Response<ItemsResponse> = articleListApi.fetchArticles(7, "")
            Truth.assertThat(articleListResponse.body()?.numResults).isEqualTo(5)
        }
    }
}