package com.marvelheroes.common.view.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    private val isVertical: Boolean = true,
    private val startSpace: Int? = null,
    private val middleSpace: Int? = null,
    private val endSpace: Int? = null
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            when {
                parent.getChildAdapterPosition(view) == 0 -> {
                    startSpace?.let {
                        if (isVertical) {
                            top = startSpace
                        } else {
                            left = startSpace
                        }
                    }
                }
                parent.getChildAdapterPosition(view) + 1 < (parent.adapter?.itemCount ?: 0) -> {
                    middleSpace?.let {
                        if (isVertical) {
                            top = middleSpace
                        } else {
                            left = middleSpace
                        }
                    }
                }
                else -> {
                    middleSpace?.let {
                        if (isVertical) {
                            top = middleSpace
                        } else {
                            left = middleSpace
                        }
                    }
                    endSpace?.let {
                        if (isVertical) {
                            bottom = endSpace
                        } else {
                            right = endSpace
                        }
                    }
                }
            }
        }
    }
}