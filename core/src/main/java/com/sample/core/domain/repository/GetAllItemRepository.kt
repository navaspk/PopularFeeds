package com.sample.core.domain.repository

import com.sample.core.domain.entity.ItemsResponse
import io.reactivex.Single

interface GetAllItemRepository {
    fun getPopularNewsItems(apiKey: String): Single<ItemsResponse>
}
