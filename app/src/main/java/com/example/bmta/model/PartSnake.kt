package com.example.bmta.model

import android.graphics.Bitmap
import android.graphics.Rect
import com.example.bmta.view.Constants
import com.example.bmta.view.GameView

class PartSnake ( var bm: Bitmap, var x: Int, var y: Int ) {
    private lateinit var rBody : Rect
    private lateinit var rTop : Rect
    private lateinit var rBot : Rect
    private lateinit var rRight : Rect
    private lateinit var rLeft : Rect

    fun getrBody() : Rect {
        return Rect(this.x, this.y, this.x + GameView.sizeOfMap, this.y + GameView.sizeOfMap)
    }

    fun getrTop() : Rect {
        return Rect(this.x, this.y - 10*Constants.SCREEN_HEIGHT/1920, this.x + GameView.sizeOfMap, this.y)
    }

    fun getrBot() : Rect {
        return Rect(this.x, this.y + GameView.sizeOfMap, this.x + GameView.sizeOfMap, this.y + GameView.sizeOfMap + 10*Constants.SCREEN_HEIGHT/1920)
    }

    fun getrRight() : Rect {
        return Rect(this.x + GameView.sizeOfMap, this.y, this.x + GameView.sizeOfMap + 10*Constants.SCREEN_WIDTH/1080, this.y + GameView.sizeOfMap)
    }

    fun getrLeft() : Rect {
        return Rect(this.x - 10*Constants.SCREEN_WIDTH/1080, this.y, this.x, this.y + GameView.sizeOfMap)
    }
}