package com.example.bmta.model

import android.graphics.Bitmap
import android.graphics.Rect

class Grass (
    var bitmap: Bitmap,
    var x: Int,
    var y: Int,
    var width: Int,
    var height: Int
    ) {
    var r: Rect

    init {
        r = Rect(x, y,x + width, y + height)
    }
}