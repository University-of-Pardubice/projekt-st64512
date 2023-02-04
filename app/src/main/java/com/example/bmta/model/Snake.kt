package com.example.bmta.model

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.bmta.view.GameView

class Snake (
    var bm: Bitmap,
    var x: Int,
    var y: Int,
    var length: Int
    ) {

    var move_left : Boolean = false

    var move_right : Boolean = true

    var move_top : Boolean = false

    var move_bottom : Boolean = false

    fun setMoveRight (moveRight : Boolean) {
        initMoves()
        move_right = moveRight
    }
    fun setMoveLeft (moveLeft : Boolean) {
        initMoves()
        move_left = moveLeft
    }
    fun setMoveTop (moveTop : Boolean) {
        initMoves()
        move_top = moveTop
    }
    fun setMoveBottom (moveBottom : Boolean) {
        initMoves()
        move_bottom = moveBottom
    }

    var arrPartSnake = ArrayList<PartSnake>()
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

        arrPartSnake.add(PartSnake(bm_head_right, x, y))
        for ( i in 1 until length-1) {
            arrPartSnake.add(PartSnake(bm_body_horizontal, arrPartSnake[i-1].x - GameView.sizeOfMap, y))
        }
        arrPartSnake.add(PartSnake(bm_tail_right, arrPartSnake[length-2].x - GameView.sizeOfMap, y))
    }

    fun update() {
        for (i in length - 1 downTo 1 ) {
            arrPartSnake[i].x = arrPartSnake[i-1].x
            arrPartSnake[i].y = arrPartSnake[i-1].y
        }

        if (move_right) {
            arrPartSnake[0].x = arrPartSnake[0].x + GameView.sizeOfMap
            arrPartSnake[0].bm = bm_head_right
        } else if (move_left) {
            arrPartSnake[0].x = arrPartSnake[0].x - GameView.sizeOfMap
            arrPartSnake[0].bm = bm_head_left
        } else if (move_top) {
            arrPartSnake[0].y = arrPartSnake[0].y - GameView.sizeOfMap
            arrPartSnake[0].bm = bm_head_up
        } else if (move_bottom) {
            arrPartSnake[0].y = arrPartSnake[0].y + GameView.sizeOfMap
            arrPartSnake[0].bm = bm_head_down
        }

        for (i in 1 until length - 1) {
            if ( arrPartSnake[i].getrLeft().intersect(arrPartSnake[i+1].getrBody())
                && arrPartSnake[i].getrBot().intersect(arrPartSnake[i-1].getrBody())
                || arrPartSnake[i].getrLeft().intersect(arrPartSnake[i-1].getrBody())
                && arrPartSnake[i].getrBot().intersect(arrPartSnake[i+1].getrBody()) ) {
                arrPartSnake[i].bm = bm_body_bot_left
            } else if ( arrPartSnake[i].getrRight().intersect(arrPartSnake[i+1].getrBody())
                && arrPartSnake[i].getrBot().intersect(arrPartSnake[i-1].getrBody())
                || arrPartSnake[i].getrRight().intersect(arrPartSnake[i-1].getrBody())
                && arrPartSnake[i].getrBot().intersect(arrPartSnake[i+1].getrBody()) ) {
                arrPartSnake[i].bm = bm_body_bot_right
            } else if ( arrPartSnake[i].getrLeft().intersect(arrPartSnake[i+1].getrBody())
                && arrPartSnake[i].getrTop().intersect(arrPartSnake[i-1].getrBody())
                || arrPartSnake[i].getrLeft().intersect(arrPartSnake[i-1].getrBody())
                && arrPartSnake[i].getrTop().intersect(arrPartSnake[i+1].getrBody()) ) {
                arrPartSnake[i].bm = bm_body_top_left
            } else if ( arrPartSnake[i].getrRight().intersect(arrPartSnake[i+1].getrBody())
                && arrPartSnake[i].getrTop().intersect(arrPartSnake[i-1].getrBody())
                || arrPartSnake[i].getrRight().intersect(arrPartSnake[i-1].getrBody())
                && arrPartSnake[i].getrTop().intersect(arrPartSnake[i+1].getrBody()) ) {
                arrPartSnake[i].bm = bm_body_top_right
            } else if ( arrPartSnake[i].getrTop().intersect(arrPartSnake[i+1].getrBody())
                && arrPartSnake[i].getrBot().intersect(arrPartSnake[i-1].getrBody())
                || arrPartSnake[i].getrTop().intersect(arrPartSnake[i-1].getrBody())
                && arrPartSnake[i].getrBot().intersect(arrPartSnake[i+1].getrBody()) ) {
                arrPartSnake[i].bm = bm_body_vertical
            } else if ( arrPartSnake[i].getrLeft().intersect(arrPartSnake[i+1].getrBody())
                && arrPartSnake[i].getrRight().intersect(arrPartSnake[i-1].getrBody())
                || arrPartSnake[i].getrLeft().intersect(arrPartSnake[i-1].getrBody())
                && arrPartSnake[i].getrRight().intersect(arrPartSnake[i+1].getrBody()) ) {
                arrPartSnake[i].bm = bm_body_horizontal
            }
        }
        if (arrPartSnake[length - 1].getrRight().intersect(arrPartSnake[length-2].getrBody())) {
            arrPartSnake[length - 1].bm = bm_tail_right
        } else if (arrPartSnake[length - 1].getrLeft().intersect(arrPartSnake[length-2].getrBody())) {
            arrPartSnake[length - 1].bm = bm_tail_left
        } else if (arrPartSnake[length - 1].getrTop().intersect(arrPartSnake[length-2].getrBody())) {
            arrPartSnake[length - 1].bm = bm_tail_up
        } else if (arrPartSnake[length - 1].getrBot().intersect(arrPartSnake[length-2].getrBody())) {
            arrPartSnake[length - 1].bm = bm_tail_down
        }
    }

    fun draw(canvas: Canvas?) {
        for (i in 0 until length) {
            canvas?.drawBitmap(arrPartSnake[i].bm, arrPartSnake[i].x.toFloat(), arrPartSnake[i].y.toFloat(), null)
        }
    }

    fun initMoves() {
        move_left = false
        move_right = false
        move_bottom = false
        move_top = false
    }

    fun grow() {
        val lastSnakePart = this.arrPartSnake[length-1]
        this.length += 1
        if (lastSnakePart.bm === bm_tail_right)
            this.arrPartSnake.add(
                PartSnake(bm_tail_right, lastSnakePart.x - GameView.sizeOfMap, lastSnakePart.y)
            )
        else if (lastSnakePart.bm === bm_tail_left)
            this.arrPartSnake.add(
                PartSnake(bm_tail_left, lastSnakePart.x + GameView.sizeOfMap, lastSnakePart.y)
            )
        else if (lastSnakePart.bm === bm_tail_up)
            this.arrPartSnake.add(
                PartSnake(bm_tail_up, lastSnakePart.x, lastSnakePart.y + GameView.sizeOfMap)
            )
        else if (lastSnakePart.bm === bm_tail_down)
            this.arrPartSnake.add(
                PartSnake(bm_tail_down, lastSnakePart.x, lastSnakePart.y - GameView.sizeOfMap)
            )
    }
}