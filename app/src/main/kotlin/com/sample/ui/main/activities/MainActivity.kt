package com.sample.ui.main.activities

import androidx.databinding.library.baseAdapters.BR
import com.sample.base.BaseActivity
import com.times.sample.R
import com.times.sample.databinding.ActivityMainBinding

/**
 * Activity with navigation graph
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    // region Variables
    override val viewModel = MainActivityViewModel::class.java
    override fun getBindingVariable() = BR._all
    override val layoutId = R.layout.activity_main
    // endregion Variables

    //region Overridden Methods
    override fun initUserInterface() {
    }
    //endregion

    // region UTIL
    fun controlMainToolBarVisibility(visibility: Int) {
        viewDataBinding.toolBar.visibility = visibility
    }
    // endregion
}


