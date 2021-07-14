package com.friendsflix.utils.extentions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setupRecyclerView(
    adapter: RecyclerView.Adapter<*>,
    @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL
) {
    this.adapter = adapter
    layoutManager = LinearLayoutManager(context, orientation, false)
}