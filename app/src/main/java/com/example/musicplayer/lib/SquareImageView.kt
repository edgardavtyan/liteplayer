package com.example.musicplayer.lib

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class SquareImageView(context: Context, attrs: AttributeSet): ImageView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredWidth)
    }
}