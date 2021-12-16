package com.sample.ui.main.fragment.home

import androidx.lifecycle.MutableLiveData
import com.sample.base.BaseViewModel
import com.sample.core.domain.entity.ItemsResponse
import com.sample.core.domain.entity.ResultsItem
import com.sample.core.domain.remote.BaseError
import com.sample.core.domain.rxcallback.OptimizedCallbackWrapper
import com.sample.core.domain.usecase.GetAllItemUseCase
import javax.inject.Inject

/**
 * View model class responsible for performing ultimate logic & get back the response.
 * This is working with help of use case and repository classes
 */
class PopularItemFragmentViewModel @Inject constructor(
    private val allItemUseCase: GetAllItemUseCase
) : BaseViewModel<PopularItemFragmentNavigator>() {

    // region VARIABLE
    var itemLiveData = MutableLiveData<ArrayList<ResultsItem>>()
    // endregion

    fun getAllPopularContents(apiKey: String) {
        addDisposable(
            allItemUseCase.execute(
                PopularNewsSubscriber(),
                GetAllItemUseCase.Params.create(apiKey)
            )
        )
    }

    inner class PopularNewsSubscriber : OptimizedCallbackWrapper<ItemsResponse>() {

        override fun onApiSuccess(response: ItemsResponse) {
            if (response.status || response.results?.isNotEmpty() == true) {
                itemLiveData.postValue(response.results as ArrayList<ResultsItem>)
            } else {
                getNavigator()?.somethingWentWrong()
            }
        }

        override fun onApiError(error: BaseError) {
            getNavigator()?.somethingWentWrong()
        }

    }
}
