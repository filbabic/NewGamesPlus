package com.babic.filip.main.ui.listener

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

private const val SCROLL_THRESHOLD = 20

class LazyLoadingListener(private inline val onBottomReached: () -> Unit) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, scrollY: Int) {
        super.onScrolled(recyclerView, dx, scrollY)

        val absoluteScroll = Math.abs(scrollY)

        if (absoluteScroll > SCROLL_THRESHOLD) {
            val layoutManager = recyclerView.layoutManager as? GridLayoutManager

            layoutManager?.run {
                if (findLastVisibleItemPosition() > itemCount - spanCount - 1) {
                    onBottomReached()
                }
            }
        }
    }
}