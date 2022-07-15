package com.sirius.test_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sirius.test_app.databinding.*
import com.sirius.test_app.loadImage
import com.sirius.test_app.model.RcItem
import com.sirius.test_app.utilities.ViewHolderBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
                ViewHolderTags(TagHolderBinding.inflate(inflater, parent, false))
            TYPE_HEADER ->
                ViewHolderRatingsHeader(RatingsHeaderViewBinding.inflate(inflater, parent, false))
            TYPE_REVIEW ->
                ViewHolderReview(ReviewViewBinding.inflate(inflater, parent, false))
            else -> error("")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ViewHolderBinder<*>).onBind(items[position])

    override fun getItemViewType(position: Int) =
        when (items[position]) {
            is RcItem.Description -> TYPE_DESCRIPTION
            is RcItem.RatingsHeader -> TYPE_HEADER
            is RcItem.Review -> TYPE_REVIEW
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
        }

        override fun onBind(item: RcItem) {
            onBind(item as RcItem.RatingsHeader)
        }
    }

    inner class ViewHolderTags(private val viewBinding: TagHolderBinding) :
        ViewHolderBinder<RcItem.Tags>(viewBinding) {
        private fun onBind(item: RcItem.Tags) = with(viewBinding) {

        }

        override fun onBind(item: RcItem) {
            onBind(item as RcItem.Tags)
        }
    }

    inner class ViewHolderReview(private val viewBinding: ReviewViewBinding) :
        ViewHolderBinder<RcItem.Review>(viewBinding) {
        private fun onBind(item: RcItem.Review) = with(viewBinding) {
            name.text = item.data.userName
            date.text = item.data.date
            scope.launch {
                loadImage(avatar, item.data.userImage)
            }
            review.text = item.data.message
        }

        override fun onBind(item: RcItem) {
            onBind(item as RcItem.Review)
        }
    }


    companion object {
        private const val TYPE_DESCRIPTION = 0
        private const val TYPE_TAGS = 1
        private const val TYPE_HEADER = 2
        private const val TYPE_REVIEW = 3
    }
}