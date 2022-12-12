package com.example.bmta.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.example.bmta.R
import com.example.bmta.model.Grass
import com.example.bmta.model.Snake
import kotlin.math.min

class GameView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val h = 21
    private val w = 12

    private val arrGrass = ArrayList<Grass>()
    private var bmGrass1: Bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.grass)
    private var bmGrass2: Bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.grass03)

    private var bmSnake: Bitmap
    private lateinit var snake: Snake

    companion object {
        @JvmField
        val sizeOfMap = min(Constants.SCREEN_WIDTH / 12, (Constants.SCREEN_HEIGHT  - 150) / 21)
    }

    init {
        this.bmGrass1 = Bitmap.createScaledBitmap(bmGrass1, sizeOfMap, sizeOfMap, true)
        this.bmGrass2 = Bitmap.createScaledBitmap(bmGrass2, sizeOfMap, sizeOfMap, true)
        this.bmSnake = BitmapFactory.decodeResource(this.resources, R.drawable.snake1);
        this.bmSnake = Bitmap.createScaledBitmap(bmSnake, 14 * sizeOfMap, sizeOfMap, true)
        for (i in 0 until this.h) {
            for (j in 0 until this.w) {
                if ( (i+j) % 2 == 0 ) {
                    arrGrass.add(
                        Grass(bmGrass1, j* sizeOfMap, i* sizeOfMap, sizeOfMap, sizeOfMap)
                    )
                } else {
                    arrGrass.add(
                        Grass(bmGrass2, j* sizeOfMap, i* sizeOfMap, sizeOfMap, sizeOfMap)
                    )
                }
            }
        }
        snake = Snake(bmSnake, arrGrass[126].x, arrGrass[126].y, 4)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        //canvas?.drawColor(0xFF1A6100)
        for (i in 0 until arrGrass.size) {
            val grassBitmap = arrGrass[i]
            canvas?.drawBitmap(grassBitmap.bitmap, grassBitmap.x.toFloat(), grassBitmap.y.toFloat(), null)
        }

        snake.draw(canvas);
    }
}