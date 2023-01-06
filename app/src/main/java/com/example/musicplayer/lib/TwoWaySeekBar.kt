package com.example.musicplayer.lib

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.widget.SeekBar

class TwoWaySeekBar(context: Context, attrs: AttributeSet): SeekBar(context, attrs) {
    private val rect = Rect()
    private val progressPaint = Paint()
    private val thumbPaint = Paint()
    private val backgroundPaint = Paint()

    var onProgressChangedListener: (Int) -> Unit = {}

    init {
        backgroundPaint.color = progressBackgroundTintList?.defaultColor ?: Color.GRAY
        progressPaint.color = progressTintList?.defaultColor ?: Color.RED
        thumbPaint.color = thumbTintList?.defaultColor ?: Color.RED

        setOnSeekBarChangeListener(object: OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) onProgressChangedListener(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }
            override fun onStopTrackingTouch(seekBar: SeekBar?) { }
        })
    }

    override fun onDraw(canvas: Canvas) {
        val top = height / 2 + 3
        val bottom = height / 2 - 2

        val leftPadding = paddingLeft - thumbOffset
        val rightPadding = paddingRight - thumbOffset
        val calcWidth = width - leftPadding - rightPadding

        rect.set(leftPadding + rightPadding, top, calcWidth, bottom)
        canvas.drawRect(rect, backgroundPaint)

        val halfMax = (max / 2).toDouble()
        val halfWidth = calcWidth / 2
        if (progress > halfMax) {
            val calcRight = ((progress - halfMax) / halfMax * halfWidth + halfWidth).toInt()
            rect.set(halfWidth + rightPadding, top, calcRight, bottom)
            canvas.drawRect(rect, progressPaint)
        }

        if (progress < halfMax) {
            val calcLeft = (progress / halfMax * halfWidth + leftPadding + rightPadding).toInt()
            rect.set(calcLeft, top, calcWidth / 2 + leftPadding, bottom)
            canvas.drawRect(rect, progressPaint)
        }

        val progressRatio = progress.toFloat() / max
        val thumbOffset = thumb.intrinsicWidth * (.5f - progressRatio)
        val thumbX: Float = progressRatio * calcWidth + rightPadding + thumbOffset
        val thumbY: Float = height / 2f
        canvas.drawCircle(thumbX, thumbY, 12f, thumbPaint)

        Log.d("progress", "TwoWaySeekBar Progress = $progress")
    }
}