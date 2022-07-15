package com.sirius.test_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sirius.test_app.databinding.TagViewBinding

class TagsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var tags = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TagViewHolder(TagViewBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as TagViewHolder).onBind(tags[position])

    override fun getItemCount() = tags.size

    inner class TagViewHolder(private val viewBinding: TagViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root)  {
        fun onBind(item: String) = with(viewBinding) {
            textView.text = item
        }
    }
}