package com.sirius.test_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sirius.test_app.databinding.*
import com.sirius.test_app.model.RcItem
import com.sirius.test_app.utilities.ViewHolderBinder
import com.sirius.test_app.utilities.setStars
import kotlinx.coroutines.CoroutineScope

class ItemsAdapter(private val scope: CoroutineScope): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = listOf<RcItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_DESCRIPTION ->
                ViewHolderDescription(DescriptionViewBinding.inflate(inflater, parent, false))
            TYPE_TAGS ->
                ViewHolderTags(RcHolderBinding.inflate(inflater, parent, false), parent.context)
            TYPE_HEADER ->
                ViewHolderRatingsHeader(RatingsHeaderViewBinding.inflate(inflater, parent, false))
            TYPE_REVIEW ->
                ViewHolderReview(RcHolderBinding.inflate(inflater, parent, false), parent.context)
            else -> error("")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ViewHolderBinder<*>).onBind(items[position])

    override fun getItemViewType(position: Int) =
        when (items[position]) {
            is RcItem.Description -> TYPE_DESCRIPTION
            is RcItem.RatingsHeader -> TYPE_HEADER
            is RcItem.Reviews -> TYPE_REVIEW
            is RcItem.Tags -> TYPE_TAGS
        }

    override fun getItemCount() = items.size

    inner class ViewHolderDescription(private val viewBinding: DescriptionViewBinding) :
        ViewHolderBinder<RcItem.Description>(viewBinding) {
        private fun onBind(item: RcItem.Description) = with(viewBinding) {
            description.text = item.text
        }

        override fun onBind(item: RcItem) {
            onBind(item as RcItem.Description)
        }
    }

    inner class ViewHolderRatingsHeader(private val viewBinding: RatingsHeaderViewBinding) :
        ViewHolderBinder<RcItem.RatingsHeader>(viewBinding) {
        private fun onBind(item: RcItem.RatingsHeader) = with(viewBinding) {
            rating.text = item.rating.toString()
            revCount.text = "${item.revCnt} reviews"
            setStars(listOf(star1, star2, star3, star4, star5), item.rating)
        }

        override fun onBind(item: RcItem) {
            onBind(item as RcItem.RatingsHeader)
        }
    }

    inner class ViewHolderTags(private val viewBinding: RcHolderBinding, private val context: Context) :
        ViewHolderBinder<RcItem.Tags>(viewBinding) {

        private val tagsAdapter = TagsAdapter()

        private fun onBind(item: RcItem.Tags) = with(viewBinding) {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = tagsAdapter
            tagsAdapter.tags = item.names
        }

        override fun onBind(item: RcItem) {
            onBind(item as RcItem.Tags)
        }
    }

    inner class ViewHolderReview(private val viewBinding: RcHolderBinding, private val context: Context) :
        ViewHolderBinder<RcItem.Reviews>(viewBinding) {

        private val reviewsAdapter = ReviewsAdapter(scope)

        private fun onBind(item: RcItem.Reviews) = with(viewBinding) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = reviewsAdapter
            reviewsAdapter.reviews = item.data
        }

        override fun onBind(item: RcItem) {
            onBind(item as RcItem.Reviews)
        }
    }


    companion object {
        private const val TYPE_DESCRIPTION = 0
        private const val TYPE_TAGS = 1
        private const val TYPE_HEADER = 2
        private const val TYPE_REVIEW = 3
    }
}