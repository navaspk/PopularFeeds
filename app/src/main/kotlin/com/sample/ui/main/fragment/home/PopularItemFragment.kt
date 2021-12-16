package com.sample.ui.main.fragment.home

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.base.BaseFragment
import com.sample.base.ItemClickListener
import com.sample.ui.main.activities.MainActivity
import com.sample.ui.main.activities.MainActivityViewModel
import com.sample.ui.main.fragment.home.adapter.PopularItemsAdapter
import com.sample.ui.main.utils.DialogManipulator
import com.sample.ui.main.utils.hasInternetConnection
import com.sample.ui.main.utils.showToast
import com.times.sample.BuildConfig
import com.times.sample.R
import com.times.sample.databinding.FragmentAllItemBinding
import javax.inject.Inject

/**
 * This is fragment is a Home screen, used to display the all content from server by making api call
 * with the help of view model class
 *
 * </p>
 *
 * Data are asynchronously get updated from livedata which defined inside view model class
 */
class PopularItemFragment : BaseFragment<FragmentAllItemBinding, PopularItemFragmentViewModel>(),
    PopularItemFragmentNavigator, ItemClickListener {

    // region VARIABLE
    private var adapter: PopularItemsAdapter? = null
    lateinit var sharedViewModel: MainActivityViewModel

    override val viewModel = PopularItemFragmentViewModel::class.java
    override fun getBindingVariable() = BR.data
    override val layoutId = R.layout.fragment_all_item

    @Inject
    lateinit var dialog: DialogManipulator
    // endregion

    //region LIFECYCLE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            sharedViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        }
        injectedViewModel.setNavigator(this)
        adapter = PopularItemsAdapter(this)
        if (hasInternetConnection())
            injectedViewModel.getAllPopularContents(BuildConfig.API_KEY)
    }

    override fun initUserInterface(view: View?) {

        (activity as MainActivity).controlMainToolBarVisibility(VISIBLE)
        if (hasInternetConnection()) {
            viewDataBinding?.progressBar?.visibility = VISIBLE
            viewDataBinding?.let {
                it.itemRecyclerView.apply {
                    setHasFixedSize(true)
                    activity?.apply {
                        layoutManager = LinearLayoutManager(this)
                    }
                    android.util.Log.d("mine", "initUserInterface and setting adapter")
                    adapter = this@PopularItemFragment.adapter
                }
            }

            // live data observer to show latest content to ui
            injectedViewModel.itemLiveData.observe(this) {
                viewDataBinding?.progressBar?.visibility = GONE
                if (it.isEmpty()) {
                    activity?.let { context ->
                        showToast(context, getString(R.string.not_data))
                    }
                } else {
                    adapter?.setItems(it)
                }
            }
        } else {
            activity?.let {
                dialog.singleButtonDialog(
                    context = it,
                    title = getString(R.string.internet_error),
                    message = getString(R.string.internet_error_message),
                    alertDialogListener = object : DialogManipulator.AlertDialogListener {
                        override fun onPositiveButtonClicked() {
                            it.finish()
                        }
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }
    //end region

    // region OVERRIDDEN
    override fun onItemClick(position: Int, view: View) {
        sharedViewModel.selectedItem = adapter?.getItems()?.get(position)
        findNavController().navigate(R.id.action_itemFragment_to_detailFragment)
    }

    override fun somethingWentWrong(responseError: Boolean) {
        viewDataBinding?.progressBar?.visibility = GONE
        activity?.let {
            if (responseError)
                showToast(it, getString(R.string.wrong_response))
            else
                showToast(it, getString(R.string.unknown_result))
        }
    }
    //endregion

}
