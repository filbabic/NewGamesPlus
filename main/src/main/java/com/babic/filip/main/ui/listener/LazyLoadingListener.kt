package com.babic.filip.main.ui.listener

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

class LazyLoadingListener(private inline val onBottomReached: () -> Unit) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager as? GridLayoutManager

        layoutManager?.run {
            if (findLastVisibleItemPosition() > itemCount - spanCount - 1) {
                onBottomReached()
            }
        }
    }
}