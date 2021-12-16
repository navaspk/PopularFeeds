package com.sample.ui.main.fragment.details

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.View.GONE
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModelProviders
import com.times.sample.R
import com.sample.base.BaseFragment
import com.sample.ui.main.activities.MainActivity
import com.sample.ui.main.activities.MainActivityViewModel
import com.times.sample.databinding.FragmentDetailsItemBinding

/**
 * Selected detail item fragment to show more detail about the feeds.
 */
class ItemDetailsFragment :
    BaseFragment<FragmentDetailsItemBinding, ItemDetailsFragmentViewModel>() {
    // region VARIABLE
    lateinit var sharedViewModel: MainActivityViewModel

    override val viewModel = ItemDetailsFragmentViewModel::class.java
    override fun getBindingVariable() = BR._all
    override val layoutId = R.layout.fragment_details_item
    // endregion

    // region LIFECYCLE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            (this as? MainActivity)?.controlMainToolBarVisibility(View.GONE)
            sharedViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        }
    }

    override fun initUserInterface(view: View?) {
        viewDataBinding?.let {
            it.webViewArticle.loadUrl(sharedViewModel.selectedItem?.url)
            Handler(Looper.getMainLooper()).postDelayed( {
                it.progressBar.visibility = GONE
            }, 300)
        }
    }


}
