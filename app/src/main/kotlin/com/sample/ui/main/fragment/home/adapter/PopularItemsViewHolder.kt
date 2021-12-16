package com.sample.ui.main.fragment.home.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sample.base.BaseViewHolder
import com.sample.core.domain.entity.ResultsItem
import com.sample.core.extensions.safeGet
import com.times.sample.R
import com.times.sample.databinding.RecyclerItemHomeBinding

/**
 * View holder class with data inflate
 */
class PopularItemsViewHolder(
    private var recyclerItem: RecyclerItemHomeBinding
) : BaseViewHolder<ResultsItem>(recyclerItem.root) {

    override fun bindItem(item: ResultsItem) {
        recyclerItem.data = item
        var url = ""
        if (item.media?.isNotEmpty() == true)
            if (item.media?.get(0)?.mediaMetadata?.isNotEmpty() == true)
                url = item.media?.get(0)?.mediaMetadata?.get(0)?.url.safeGet()
        if (url.isEmpty().not())
            Glide.with(recyclerItem.feedsArticleImage.context)
                .load(url)
                .error(R.drawable.feeds)
                .placeholder(R.drawable.feeds)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(recyclerItem.feedsArticleImage)
    }

}

