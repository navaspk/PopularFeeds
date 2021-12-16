package com.sample.core.domain.usecase

import com.sample.core.domain.SingleUseCase
import com.sample.core.domain.entity.ItemsResponse
import com.sample.core.domain.executor.PostExecutionThread
import com.sample.core.domain.repository.GetAllItemRepository
import com.sample.core.extensions.safeGet
import io.reactivex.Single
import javax.inject.Inject

class GetAllItemUseCase @Inject constructor(
    private val getItemRepository: GetAllItemRepository,
    private val postExecutionThread: PostExecutionThread
) : SingleUseCase<ItemsResponse, GetAllItemUseCase.Params>(
    postExecutionThread
) {

    override fun buildUseCase(params: Params?): Single<ItemsResponse> {
        return getItemRepository.getPopularNewsItems(params?.apiKey.safeGet())
    }

    data class Params constructor(
        val apiKey: String = ""
    ) {
        companion object {
            fun create(apiKey: String) =
                Params(
                    apiKey
                )
        }
    }

}
