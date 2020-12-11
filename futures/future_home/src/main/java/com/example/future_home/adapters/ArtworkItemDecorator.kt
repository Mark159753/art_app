package com.example.future_home.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ArtworkItemDecorator(
        private val verticalSpace:Int,
        private val horizontalSpace:Int,
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect){
            val position = parent.getChildAdapterPosition(view) - 1

            if (position == -1 || position == 0){
                left = horizontalSpace
            }
            if (position % 3 == 0){
                bottom = verticalSpace
            }
            right = horizontalSpace
            top = verticalSpace

        }
    }
}