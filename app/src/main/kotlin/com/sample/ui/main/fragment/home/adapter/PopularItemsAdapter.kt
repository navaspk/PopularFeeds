package com.sample.ui.main.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sample.base.BaseRecyclerAdapter
import com.sample.base.BaseViewHolder
import com.sample.base.ItemClickListener
import com.sample.core.domain.entity.ResultsItem
import com.times.sample.databinding.RecyclerItemHomeBinding

/**
 * Adapter with view holder instantiation
 */
class PopularItemsAdapter(
    itemClickListener: ItemClickListener
) : BaseRecyclerAdapter<ResultsItem>(itemClickListener) {

    override fun createBaseViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ResultsItem> {
        return PopularItemsViewHolder(
            RecyclerItemHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

}
