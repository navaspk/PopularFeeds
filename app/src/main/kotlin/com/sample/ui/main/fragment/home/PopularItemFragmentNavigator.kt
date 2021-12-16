package com.sample.ui.main.fragment.home

import com.sample.base.BaseNavigator
import com.sample.core.domain.entity.ResultsItem

interface PopularItemFragmentNavigator: BaseNavigator {

    fun somethingWentWrong(responseError: Boolean = false)

}
