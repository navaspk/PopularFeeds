package com.sample.core.domain.api

import com.sample.core.domain.entity.ItemsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("svc/mostpopular/v2/mostviewed/all-sections/1.json")
    fun getPopularItemToShow(@Query("api-key") apiKey: String): Single<ItemsResponse>

}
