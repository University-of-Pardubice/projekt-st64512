package com.example.bmta.model

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import com.example.bmta.view.GameView

class Apple(
    var bm: Bitmap,
    var x: Int,
    var y: Int
) {
    var r: Rect

    init {
        r = Rect(x, y,x + GameView.sizeOfMap, y + GameView.sizeOfMap)
    }

    fun draw(canvas: Canvas?) {
        canvas?.drawBitmap(bm, x.toFloat(), y.toFloat(), null)
    }

    fun reset(newX: Int, newY: Int) {
        x = newX
        y = newY
        r = Rect(x, y,x + GameView.sizeOfMap, y + GameView.sizeOfMap)
    }
}