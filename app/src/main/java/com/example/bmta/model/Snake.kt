package com.example.bmta.model

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import com.example.bmta.view.GameView

class Snake (
    var bm: Bitmap,
    var x: Int,
    var y: Int,
    var length: Int
    ) {
    private var arrPartSnake = ArrayList<PartSnake>()
    private var bm_head_up : Bitmap
    private var bm_head_down : Bitmap
    private var bm_head_left : Bitmap
    private var bm_head_right : Bitmap

    private var bm_body_vertical : Bitmap
    private var bm_body_horizontal : Bitmap

    private var bm_body_top_right : Bitmap
    private var bm_body_top_left : Bitmap
    private var bm_body_bot_right : Bitmap
    private var bm_body_bot_left : Bitmap

    private var bm_tail_up : Bitmap
    private var bm_tail_down : Bitmap
    private var bm_tail_left : Bitmap
    private var bm_tail_right : Bitmap

    init {
        bm_body_bot_left = Bitmap.createBitmap(bm, 0, 0, GameView.sizeOfMap, GameView.sizeOfMap)
        bm_body_bot_right = Bitmap.createBitmap(bm, GameView.sizeOfMap, 0, GameView.sizeOfMap, GameView.sizeOfMap)
        bm_body_horizontal = Bitmap.createBitmap(bm, 2*GameView.sizeOfMap, 0, GameView.sizeOfMap, GameView.sizeOfMap)
        bm_body_top_left = Bitmap.createBitmap(bm, 3*GameView.sizeOfMap, 0, GameView.sizeOfMap, GameView.sizeOfMap)
        bm_body_top_right = Bitmap.createBitmap(bm, 4*GameView.sizeOfMap, 0, GameView.sizeOfMap, GameView.sizeOfMap)
        bm_body_vertical = Bitmap.createBitmap(bm, 5*GameView.sizeOfMap, 0, GameView.sizeOfMap, GameView.sizeOfMap)
        bm_head_down = Bitmap.createBitmap(bm, 6*GameView.sizeOfMap, 0, GameView.sizeOfMap, GameView.sizeOfMap)
        bm_head_left = Bitmap.createBitmap(bm, 7*GameView.sizeOfMap, 0, GameView.sizeOfMap, GameView.sizeOfMap)
        bm_head_right = Bitmap.createBitmap(bm, 8*GameView.sizeOfMap, 0, GameView.sizeOfMap, GameView.sizeOfMap)
        bm_head_up = Bitmap.createBitmap(bm, 9*GameView.sizeOfMap, 0, GameView.sizeOfMap, GameView.sizeOfMap)
        bm_tail_up = Bitmap.createBitmap(bm, 10*GameView.sizeOfMap, 0, GameView.sizeOfMap, GameView.sizeOfMap)
        bm_tail_right = Bitmap.createBitmap(bm, 11*GameView.sizeOfMap, 0, GameView.sizeOfMap, GameView.sizeOfMap)
        bm_tail_left = Bitmap.createBitmap(bm, 12*GameView.sizeOfMap, 0, GameView.sizeOfMap, GameView.sizeOfMap)
        bm_tail_down = Bitmap.createBitmap(bm, 13*GameView.sizeOfMap, 0, GameView.sizeOfMap, GameView.sizeOfMap)

        arrPartSnake.add(PartSnake(bm_head_right, x, y));
        for ( i in 1 until length-1) {
            arrPartSnake.add(PartSnake(bm_body_horizontal, arrPartSnake[i-1].x - GameView.sizeOfMap, y));
        }
        arrPartSnake.add(PartSnake(bm_tail_right, arrPartSnake[length-2].x - GameView.sizeOfMap, y));
    }

    fun draw(canvas: Canvas?) {
        for (i in 0 until length) {
            canvas?.drawBitmap(arrPartSnake[i].bm, arrPartSnake[i].x.toFloat(), arrPartSnake[i].y.toFloat(), null)
        }
    }
}