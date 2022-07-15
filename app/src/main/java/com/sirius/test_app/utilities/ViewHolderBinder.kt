package com.sirius.test_app.utilities

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sirius.test_app.model.RcItem

abstract class ViewHolderBinder<in T : RcItem>(private val viewBinding: ViewBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    abstract fun onBind(item: RcItem)
    fun isAssignable(item: RcItem): Boolean = item.javaClass.isAssignableFrom(this.javaClass)
}