package com.sample.core.domain.repository.impl

import com.sample.core.domain.api.ApiServices
import com.sample.core.domain.entity.ItemsResponse
import com.sample.core.domain.repository.GetAllItemRepository
import io.reactivex.Single
import javax.inject.Inject

class GetAllItemRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices
) : GetAllItemRepository {

    override fun getPopularNewsItems(apiKey: String): Single<ItemsResponse> {
        return apiServices.getPopularItemToShow(apiKey)
    }
}
