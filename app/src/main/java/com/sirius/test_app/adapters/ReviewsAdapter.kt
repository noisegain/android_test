package com.sirius.test_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sirius.test_app.ReviewModel
import com.sirius.test_app.databinding.ReviewViewBinding
import com.sirius.test_app.utilities.loadImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ReviewsAdapter(private val scope: CoroutineScope): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var reviews = listOf<ReviewModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReviewViewHolder(ReviewViewBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ReviewViewHolder).onBind(reviews[position])

    override fun getItemCount() = reviews.size

    inner class ReviewViewHolder(private val viewBinding: ReviewViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root)  {
        fun onBind(item: ReviewModel) = with(viewBinding) {
            name.text = item.userName
            date.text = item.date
            scope.launch {
                loadImage(avatar, item.userImage)
            }
            review.text = item.message
        }
    }
}